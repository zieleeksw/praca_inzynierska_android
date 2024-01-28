package com.example.praca_inzynierska.components.food.components.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.data.Food
import com.example.praca_inzynierska.view.models.food.FoodScreenViewModel

import com.example.praca_inzynierska.view.models.food.FoodScreenViewModel

@Composable
fun ExpandableContent(
    isExpanded: Boolean,
    filteredFood: List<Food>,
    viewModel: FoodScreenViewModel
) {

    val expansionAnimationDuration = 300

    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(expansionAnimationDuration)
        ) + fadeIn(
            initialAlpha = .3f,
            animationSpec = tween(expansionAnimationDuration)
        )
    }
    val exitTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(expansionAnimationDuration)
        ) + fadeOut(animationSpec = tween(expansionAnimationDuration))
    }

    AnimatedVisibility(visible = isExpanded, enter = enterTransition, exit = exitTransition) {
        Box(
            modifier = Modifier
                .background(color = colorResource(id = R.color.secondary_color))
        ) {
            LazyColumn {
                items(
                    items = filteredFood,
                    key = { food -> food.id }
                ) { filteredFoodObject ->
                    ExpandableCardItemComponent(filteredFoodObject, viewModel)
                }
            }
        }
    }
}