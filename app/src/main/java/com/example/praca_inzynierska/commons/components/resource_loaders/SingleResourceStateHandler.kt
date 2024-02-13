package com.example.praca_inzynierska.commons.components.resource_loaders

import androidx.compose.runtime.Composable
import com.example.praca_inzynierska.commons.states.SingleResourceState

@Composable
fun SingleResourceStateHandler(
    resourceState: SingleResourceState<*>,
    content: @Composable () -> Unit
) {
    when {
        resourceState.loading -> CustomCircularProgressIndicator()
        resourceState.error != null -> OnFetchDataErrorComponent()
        else -> content()
    }
}