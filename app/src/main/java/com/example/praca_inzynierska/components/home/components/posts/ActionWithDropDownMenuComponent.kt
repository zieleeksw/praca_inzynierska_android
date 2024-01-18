package com.example.praca_inzynierska.components.home.components.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ActionWithDropDownMenuComponent(
    onOptionSelected: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Newest") }

    Column {
        IconButton(onClick = { expanded = true }) {
            Icon(imageVector = Icons.Default.FilterList, contentDescription = null)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(Color.White)
        ) {
            DropdownMenuItem(text = { Text("Newest") }, onClick = {
                selectedOption = "Newest"
                expanded = false
                onOptionSelected(selectedOption)
            })
            DropdownMenuItem(text = {
                Text(text = "Followed")
            }, onClick = {
                selectedOption = "Followed"
                expanded = false
                onOptionSelected(selectedOption)
            })
            DropdownMenuItem(text = { Text("Oldest") }, onClick = {
                selectedOption = "Oldest"
                expanded = false
                onOptionSelected(selectedOption)
            })
            DropdownMenuItem(text = { Text("Mine") }, onClick = {
                selectedOption = "Mine"
                expanded = false
                onOptionSelected(selectedOption)
            })
        }
    }
}