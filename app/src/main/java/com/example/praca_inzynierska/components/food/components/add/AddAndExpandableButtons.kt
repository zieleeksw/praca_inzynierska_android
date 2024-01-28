package com.example.praca_inzynierska.components.food.components.add

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.screens.Screens

@Composable
fun AddButtonAndExpandableButtons(
    expanded: Boolean,
    onToggleExpanded: () -> Unit,
    navController: NavHostController,
    meal: String,
    date: String
) {
    val transition = updateTransition(targetState = expanded, label = "trans")
    val iconRotationDeg by transition.animateFloat(label = "icon change") { state ->
        if (state) {
            0f
        } else {
            180f
        }
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Icon",
            tint = Color.White,
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.secondary_color),
                    shape = CircleShape
                )
                .clickable {
                    navController.navigate(
                        "${Screens.AddProductScreen.name}/${date}/${meal}"
                    )
                }
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = null,
            modifier = Modifier
                .rotate(iconRotationDeg)
                .clickable { onToggleExpanded() },
            tint = Color.White
        )
    }
}