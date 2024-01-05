package com.example.praca_inzynierska.screens

import android.util.Log
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
import com.example.praca_inzynierska.login.register.components.buttons.ConfirmButtonComponent
import com.example.praca_inzynierska.login.register.components.textfields.ConfirmPasswordInputWithErrorComponent
import com.example.praca_inzynierska.login.register.components.textfields.EmailInputWithErrorComponent
import com.example.praca_inzynierska.login.register.components.textfields.PasswordInputWithErrorComponent
import com.example.praca_inzynierska.login.register.components.textfields.UsernameInputWithErrorComponent
import com.example.praca_inzynierska.login.register.components.texts.CenteredHeadingComponent
import com.example.praca_inzynierska.login.register.components.texts.CenteredNormalTextComponent
import com.example.praca_inzynierska.login.register.components.texts.ClickableLoginTextComponent
import com.example.praca_inzynierska.login.register.components.texts.DividerTextComponent
import com.example.praca_inzynierska.viewModels.RegisterViewModel


@Composable
fun RegisterScreen(
    navController: NavController
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {

        val viewModel = viewModel<RegisterViewModel>()
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

                    else -> {
                        Log.e(
                            "SOMETHING_WENT_WRONG", "Register screen went into " +
                                    "illegal state exception"
                        )
                    }
                }
            }
        }

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
                errorMessage = viewModel.state.emailError,
                onPasswordChanged = { password -> viewModel.onPasswordChanged(password) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            ConfirmPasswordInputWithErrorComponent(viewModel = viewModel)
            Spacer(modifier = Modifier.height(36.dp))
            ConfirmButtonComponent(
                text = stringResource(id = R.string.register_button),
                256.dp,
                onClick = { viewModel.onSubmit() })
            DividerTextComponent()
            ClickableLoginTextComponent(
                initialText = "Already have an account? ",
                clickableText = "Login",
                onTextSelected = {
                    navController.navigate("loginscreen")
                })
        }
    }
}