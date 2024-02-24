package com.example.praca_inzynierska.settings.components.food_chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.objects.Ui
import com.example.praca_inzynierska.settings.enums.SelectedNutrientType
import com.example.praca_inzynierska.settings.vm.FoodChartViewModel

@Composable
fun DropDownMenuFood(viewModel: FoodChartViewModel) {

    var expanded by remember { mutableStateOf(false) }
    val items = SelectedNutrientType.entries.toTypedArray()
    val selectedItem = viewModel.selectedNutrientType.value

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { expanded = !expanded },
                shape = Ui.DEFAULT_ROUNDED_CORNER_SHAPE,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.secondary_color),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = selectedItem.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                    contentDescription = "Dropdown Arrow",
                    tint = Color.White
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        modifier = Modifier.background(color = Color.White),
                        text = { Text(text = item.name) },
                        onClick = {
                            viewModel.onNutrientTypeChanged(item)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DropDownMenu() {
    DropDownMenuFood(FoodChartViewModel())
}