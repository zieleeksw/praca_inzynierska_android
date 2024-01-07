package com.example.praca_inzynierska.screens


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.components.ConfirmButtonComponent
import com.example.praca_inzynierska.components.diet_configuration.ActivityLevelChooserComponent
import com.example.praca_inzynierska.components.diet_configuration.DateSelectorWithErrorComponent
import com.example.praca_inzynierska.components.diet_configuration.EnterValueOutlinedTextFieldWithError
import com.example.praca_inzynierska.components.diet_configuration.GenderSelectorComponent
import com.example.praca_inzynierska.viewModels.DietConfigurationViewModel

@Composable
fun DietConfigurationScreen(
    navController: NavController,
    userId: Long?,
    token: String?
) {

    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focusManager.clearFocus() })
            }
    ) {

        val viewModel = viewModel<DietConfigurationViewModel>()
        viewModel.userId = userId
        viewModel.token = token!!
        val context = LocalContext.current

        LaunchedEffect(key1 = context) {
            viewModel.validationEvents.collect { event ->
                when (event) {
                    is ValidationEvent.Success -> {
                        Toast.makeText(
                            context, "Data filled successfully",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    else -> {
                        Log.e(
                            "SOMETHING_WENT_WRONG", "Register screen went into " +
                                    "illegal state exception"
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
            EnterValueOutlinedTextFieldWithError(
                headerText = "Current weight",
                textFieldValue = "KG",
                numOfTakenCharacters = 4,
                isError = viewModel.state.currentWeightError != null,
                errorString = viewModel.state.currentWeightError,
                value = viewModel.state.currentWeight,
                onTextFieldChanged = { weight -> viewModel.onCurrentWeightChanged(weight) }
            )
            EnterValueOutlinedTextFieldWithError(
                headerText = "Target weight",
                textFieldValue = "KG",
                numOfTakenCharacters = 4,
                isError = viewModel.state.targetWeightError != null,
                errorString = viewModel.state.targetWeightError,
                value = viewModel.state.targetWeight,
                onTextFieldChanged = { weight -> viewModel.onTargetWeightChanged(weight) }
            )
            ConfirmButtonComponent(text = "Next", 140.dp, onClick = { viewModel.onSubmit() })
        }
    }
}