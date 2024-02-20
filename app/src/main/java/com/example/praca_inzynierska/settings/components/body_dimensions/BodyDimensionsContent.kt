package com.example.praca_inzynierska.settings.components.body_dimensions

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.settings.vm.BodyDimensionsViewModel


@Composable
fun BodyDimensionsContent(
    viewModel: BodyDimensionsViewModel
) {
    Spacer(modifier = Modifier.height(4.dp))
    LazyColumn {
        items(viewModel.bodyDimensions.value.list) { bodyDimensions ->
            BodyDimensionsCard(
                bodyDimensions = bodyDimensions,
                onDelete = { viewModel.deleteDimensions(it) }
            )
        }
    }
}