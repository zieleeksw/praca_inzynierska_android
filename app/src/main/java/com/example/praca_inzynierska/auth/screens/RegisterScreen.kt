package com.example.praca_inzynierska.auth.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.auth.components.CenteredHeadingComponent
import com.example.praca_inzynierska.auth.components.CenteredNormalTextComponent
import com.example.praca_inzynierska.auth.components.ClickableLoginTextComponent
import com.example.praca_inzynierska.auth.components.ConfirmPasswordInputWithErrorComponent
import com.example.praca_inzynierska.auth.components.DividerTextComponent
import com.example.praca_inzynierska.auth.components.EmailInputWithErrorComponent
import com.example.praca_inzynierska.auth.components.PasswordInputWithErrorComponent
import com.example.praca_inzynierska.auth.components.UsernameInputWithErrorComponent
import com.example.praca_inzynierska.auth.vm.RegisterViewModel
import com.example.praca_inzynierska.commons.components.ConfirmButtonComponent
import com.example.praca_inzynierska.commons.screens.Screens


@Composable
fun RegisterScreen(
    navController: NavController
) {

    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focusManager.clearFocus() })
            }
    ) {

        val viewModel = viewModel<RegisterViewModel>()

        Column(modifier = Modifier.fillMaxSize()) {
            CenteredNormalTextComponent(text = stringResource(id = R.string.welcome))
            CenteredHeadingComponent(text = stringResource(id = R.string.register))
            UsernameInputWithErrorComponent(viewModel = viewModel)
            Spacer(modifier = Modifier.height(8.dp))
            EmailInputWithErrorComponent(
                email = viewModel.state.email,
                isError = viewModel.state.emailError != null,
                errorMessage = viewModel.state.emailError,
                onEmailChanged = { email -> viewModel.onEmailChanged(email) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            PasswordInputWithErrorComponent(
                password = viewModel.state.password,
                isError = viewModel.state.passwordError != null,
                errorMessage = viewModel.state.passwordError,
                onPasswordChanged = { password -> viewModel.onPasswordChanged(password) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            ConfirmPasswordInputWithErrorComponent(viewModel = viewModel)
            Spacer(modifier = Modifier.height(36.dp))
            ConfirmButtonComponent(
                text = stringResource(id = R.string.register_button),
                340.dp,
                onClick = {
                    viewModel.onSubmit {
                        navController.navigate(Screens.LoginScreen.name)
                    }
                })
            DividerTextComponent()
            ClickableLoginTextComponent(
                initialText = "Already have an account? ",
                clickableText = "Login",
                onTextSelected = {
                    navController.navigate(Screens.LoginScreen.name)
                })
        }
    }
}