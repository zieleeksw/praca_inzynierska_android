package com.example.praca_inzynierska.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.components.ButtonComponent
import com.example.praca_inzynierska.components.ClickableLoginTextComponent
import com.example.praca_inzynierska.components.DividerTextComponent
import com.example.praca_inzynierska.components.HeadingTextComponent
import com.example.praca_inzynierska.components.NormalTextComponent
import com.example.praca_inzynierska.components.textfields.EmailInputWithErrorComponent
import com.example.praca_inzynierska.components.textfields.PasswordInputWithErrorComponent
import com.example.praca_inzynierska.viewModels.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {

        val viewModel = viewModel<LoginViewModel>()
        val context = LocalContext.current

        LaunchedEffect(key1 = context) {
            viewModel.validationEvents.collect { event ->
                when (event) {
                    is ValidationEvent.Success -> {
                        Toast.makeText(
                            context, "Register successful",
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
            NormalTextComponent(text = stringResource(id = R.string.welcome))
            HeadingTextComponent(text = stringResource(id = R.string.login))
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
            ButtonComponent(text = stringResource(id = R.string.login_button), viewModel)
            DividerTextComponent()
            ClickableLoginTextComponent(
                initialText = "Don't have an acoount?  ",
                clickableText = "Sign up",
                onTextSelected = {
                navController.navigate("registerscreen")
            })
        }
    }
}