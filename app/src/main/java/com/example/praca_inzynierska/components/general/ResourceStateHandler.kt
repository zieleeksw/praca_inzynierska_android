package com.example.praca_inzynierska.components.general

import androidx.compose.runtime.Composable
import com.example.praca_inzynierska.components.CustomCircularProgressIndicator
import com.example.praca_inzynierska.components.OnFetchDataErrorComponent
import com.example.praca_inzynierska.commons.states.ResourceState

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