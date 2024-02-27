package com.example.praca_inzynierska.diet_configuration.screens


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.commons.components.ConfirmButtonComponent
import com.example.praca_inzynierska.commons.screens.Screens
import com.example.praca_inzynierska.diet_configuration.components.ActivityLevelChooserComponent
import com.example.praca_inzynierska.diet_configuration.components.DateSelectorWithErrorComponent
import com.example.praca_inzynierska.diet_configuration.components.EnterValueOutlinedTextFieldWithError
import com.example.praca_inzynierska.diet_configuration.components.GenderSelectorComponent
import com.example.praca_inzynierska.diet_configuration.components.HeaderTextConfigurationScreenComponent
import com.example.praca_inzynierska.diet_configuration.components.WeightRowComponent
import com.example.praca_inzynierska.diet_configuration.vm.DietConfigurationViewModel

@Composable
fun DietConfigurationScreen(
    navController: NavController
) {

    val focusManager = LocalFocusManager.current
    val viewModel = viewModel<DietConfigurationViewModel>()
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {
                    navController.navigate(Screens.MainContent.name) {
                        popUpTo(Screens.LoginScreen.name) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }

                else -> {
                    Log.e(
                        "SOMETHING_WENT_WRONG", "DietConfigurationScreen went into " +
                                "illegal state exception"
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.light_gray))
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focusManager.clearFocus() })
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        GenderSelectorComponent(viewModel)
        DateSelectorWithErrorComponent(viewModel)
        ActivityLevelChooserComponent(viewModel)
        EnterValueOutlinedTextFieldWithError(
            headerText = "Height",
            textFieldValue = "CM",
            numOfTakenCharacters = 3,
            isError = viewModel.state.heightError != null,
            errorString = viewModel.state.heightError,
            value = viewModel.state.height,
            onTextFieldChanged = { height -> viewModel.onHeightChanged(height) }
        )
        HeaderTextConfigurationScreenComponent(text = "Weight")
        WeightRowComponent(viewModel)
        ConfirmButtonComponent(text = "Next", 140.dp, onClick = { viewModel.onSubmit() })
    }
}