package com.example.praca_inzynierska.diet_configuration.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.diet_configuration.enums.ActivityLevel
import com.example.praca_inzynierska.diet_configuration.vm.DietConfigurationViewModel

@Composable
fun ActivityLevelChooserComponent(
    viewModel: DietConfigurationViewModel
) {

    var isExpanded by remember { mutableStateOf(false) }

    Column {
        HeaderTextConfigurationScreenComponent(text = "Choose your activity level")
        Spacer(Modifier.height(12.dp))
        Button(
            contentPadding = PaddingValues(),
            onClick = { isExpanded = true },
            modifier = Modifier
                .heightIn(48.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(140.dp)
                    .heightIn(48.dp)
                    .background(
                        shape = RoundedCornerShape(50.dp),
                        brush = Brush.horizontalGradient(
                            listOf(
                                colorResource(id = R.color.secondary_color),
                                colorResource(id = R.color.primary_color)
                            )
                        )
                    )
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    Text(
                        viewModel.state.activityLevel.text, style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = ""
                    )
                }
            }
            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                DropdownMenuItem(text = {
                    Text(text = "Very low")
                }, onClick = {
                    isExpanded = false
                    viewModel.onActivityLevelChanged(ActivityLevel.VERY_LOW)
                }
                )
                DropdownMenuItem(text = { Text("Low") }, onClick = {
                    isExpanded = false
                    viewModel.onActivityLevelChanged(ActivityLevel.LOW)
                })
                DropdownMenuItem(text = { Text("Moderately") }, onClick = {
                    isExpanded = false
                    viewModel.onActivityLevelChanged(ActivityLevel.MODERATELY)
                })
                DropdownMenuItem(text = { Text("Active") }, onClick = {
                    isExpanded = false
                    viewModel.onActivityLevelChanged(ActivityLevel.ACTIVE)
                })
                DropdownMenuItem(text = { Text("Very active") }, onClick = {
                    isExpanded = false
                    viewModel.onActivityLevelChanged(ActivityLevel.VERY_ACTIVE)
                })
            }
        }
    }
}