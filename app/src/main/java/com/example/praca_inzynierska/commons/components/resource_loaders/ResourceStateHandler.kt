package com.example.praca_inzynierska.commons.components.resource_loaders

import androidx.compose.runtime.Composable
import com.example.praca_inzynierska.commons.states.ResourceState
import com.example.praca_inzynierska.components.CustomCircularProgressIndicator
import com.example.praca_inzynierska.components.OnFetchDataErrorComponent

@Composable
fun ResourceStateHandler(
    resourceState: ResourceState<*>,
    content: @Composable () -> Unit
) {
    when {
        resourceState.loading -> CustomCircularProgressIndicator()
        resourceState.error != null -> OnFetchDataErrorComponent()
        else -> content()
    }
}