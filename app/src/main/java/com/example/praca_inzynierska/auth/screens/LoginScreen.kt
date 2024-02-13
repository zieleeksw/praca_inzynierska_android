package com.example.praca_inzynierska.auth.screens

import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.commons.components.ConfirmButtonComponent
import com.example.praca_inzynierska.auth.components.EmailInputWithErrorComponent
import com.example.praca_inzynierska.auth.components.PasswordInputWithErrorComponent
import com.example.praca_inzynierska.auth.components.CenteredHeadingComponent
import com.example.praca_inzynierska.auth.components.CenteredNormalTextComponent
import com.example.praca_inzynierska.auth.components.ClickableLoginTextComponent
import com.example.praca_inzynierska.auth.components.DividerTextComponent
import com.example.praca_inzynierska.auth.vm.LoginViewModel
import com.example.praca_inzynierska.commons.screens.Screens

@Composable
fun LoginScreen(
    navController: NavController,
) {

    val focusManager = LocalFocusManager.current
    val viewModel = viewModel<LoginViewModel>()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focusManager.clearFocus() })
            }
    )
    {
        LaunchedEffect(key1 = context) {
            viewModel.validationEvents.collect { event ->
                when (event) {
                    is ValidationEvent.Success -> {
                        if (viewModel.user?.userNutritionConfig == null) {
                            navController.navigate(Screens.DietConfigurationScreen.name)
                        } else {
                            navController.navigate(Screens.MainContent.name)
                        }
                        Toast.makeText(
                            context, "Logged successful",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    is ValidationEvent.Failure ->
                        Toast.makeText(
                            context, "Something went wrong. Try again later",
                            Toast.LENGTH_LONG
                        ).show()

                    is ValidationEvent.BadCredentials ->
                        Toast.makeText(
                            context, "Incorrect login or password",
                            Toast.LENGTH_LONG
                        ).show()
                }
            }
        }
        Column(modifier = Modifier.fillMaxSize()) {
            CenteredNormalTextComponent(text = stringResource(id = R.string.welcome))
            CenteredHeadingComponent(text = stringResource(id = R.string.login))
            EmailInputWithErrorComponent(
                email = viewModel.state.email,
                isError = viewModel.state.loginError,
                errorMessage = null,
                onEmailChanged = { email -> viewModel.onEmailChanged(email) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordInputWithErrorComponent(
                password = viewModel.state.password,
                isError = viewModel.state.loginError,
                errorMessage = null,
                onPasswordChanged = { password -> viewModel.onPasswordChanged(password) }
            )
            Spacer(modifier = Modifier.height(36.dp))
            ConfirmButtonComponent(
                text = stringResource(id = R.string.login_button),
                340.dp,
                onClick = { viewModel.onSubmit() })
            DividerTextComponent()
            ClickableLoginTextComponent(
                initialText = "Don't have an acoount?  ",
                clickableText = "Sign up",
                onTextSelected = {
                    navController.navigate(Screens.RegisterScreen.name)
                })
        }
    }
}