package com.example.praca_inzynierska.commons.objects

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object Global {
    var currentUserId: Long by mutableLongStateOf(0)
    var token: String by mutableStateOf("")
}