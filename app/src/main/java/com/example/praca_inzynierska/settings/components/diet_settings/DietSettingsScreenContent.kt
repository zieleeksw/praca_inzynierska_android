package com.example.praca_inzynierska.settings.components.diet_settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.settings.components.exercise_chart.LockedFieldsComponents
import com.example.praca_inzynierska.settings.components.exercise_chart.StatsTextField
import com.example.praca_inzynierska.settings.vm.DietSettingsScreenViewModel

@Composable
fun DietSettingsScreenContent(
    viewModel: DietSettingsScreenViewModel
) {

    var showActivityLevelDialog by remember { mutableStateOf(false) }
    var showCurrentWeightDialog by remember { mutableStateOf(false) }
    var showTargetWeightDialog by remember { mutableStateOf(false) }

    if (showCurrentWeightDialog) {
        WeightDialog(
            initialWeight = viewModel.userConfigState.value.resource!!.currentWeight.toString(),
            onDismiss = { showCurrentWeightDialog = false },
            onOk = { newWeight ->
                viewModel.updateCurrentWeight(newWeight)
                showCurrentWeightDialog = false
            }
        )
    }

    if (showTargetWeightDialog) {
        WeightDialog(
            initialWeight = viewModel.userConfigState.value.resource!!.targetWeight.toString(),
            onDismiss = { showTargetWeightDialog = false },
            onOk = { newWeight ->
                viewModel.updateTargetWeight(newWeight)
                showTargetWeightDialog = false
            }
        )
    }

    if (showActivityLevelDialog) {
        ActivityLevelDialog(
            onDismiss = { showActivityLevelDialog = false },
            onOk = {
                viewModel.changeActivityLevel(it)
                showActivityLevelDialog = false
            },
        )
    }

    Text(
        text = "Weight gain/loss is 0.5 kg per week.",
        color = Color.White,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.secondary_color))
            .padding(vertical = 8.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))
    LockedFieldsComponents(
        leftLabelTitle = "Height",
        leftLabelValue = viewModel.userConfigState.value.resource!!.height.toString(),
        rightLabelTitle = "Date of birth",
        rightLabelValue = viewModel.userConfigState.value.resource!!.dob
    )
    Spacer(Modifier.height(8.dp))
    LockedFieldsComponents(
        leftLabelTitle = "Gender",
        leftLabelValue = viewModel.userConfigState.value.resource!!.gender,
        rightLabelTitle = "Diet status",
        rightLabelValue = viewModel.userConfigState.value.resource!!.dietStatus
    )
    Spacer(Modifier.height(8.dp))
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StatsTextField(
            value = viewModel.userConfigState.value.resource!!.dietFinish,
            label = "Diet Finish"
        )
    }
    Spacer(Modifier.height(8.dp))
    EditableStatsTextField(
        label = "Activity Level",
        value = viewModel.userConfigState.value.resource!!.activityLevel,
        onEditClick = {
            showActivityLevelDialog = true
        }
    )
    Spacer(Modifier.height(8.dp))
    EditableStatsTextField(
        label = "Current weight",
        value = viewModel.userConfigState.value.resource!!.currentWeight.toString(),
        onEditClick = {
            showCurrentWeightDialog = true
        }
    )
    Spacer(Modifier.height(8.dp))
    EditableStatsTextField(
        label = "Target weight",
        value = viewModel.userConfigState.value.resource!!.targetWeight.toString(),
        onEditClick = {
            showTargetWeightDialog = true
        }
    )
}