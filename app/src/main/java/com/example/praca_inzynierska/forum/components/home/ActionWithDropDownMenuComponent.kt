package com.example.praca_inzynierska.forum.components.home

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
import com.example.praca_inzynierska.forum.vm.HomeScreenViewModel

@Composable
fun ActionWithDropDownMenuComponent(
    viewModel: HomeScreenViewModel
) {

    var expanded by remember { mutableStateOf(false) }

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
                expanded = false
                viewModel.onFilterChanged("Newest")
            })
            DropdownMenuItem(text = {
                Text(text = "Followed")
            }, onClick = {
                expanded = false
                viewModel.onFilterChanged("Followed")
            })
            DropdownMenuItem(text = { Text("Oldest") }, onClick = {
                expanded = false
                viewModel.onFilterChanged("Oldest")
            })
            DropdownMenuItem(text = { Text("Mine") }, onClick = {
                expanded = false
                viewModel.onFilterChanged("Mine")
            })
        }
    }
}