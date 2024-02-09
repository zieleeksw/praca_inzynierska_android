package com.example.praca_inzynierska.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.screens.Screens

@Composable
fun SearchTextFieldWithColumnItem(
    list: List<String>,
    navController: NavHostController,
    date: String
) {

    val textState = remember { mutableStateOf(TextFieldValue("")) }

    SearchTextField(state = textState, placeHolder = "Search here...")
    LazyColumn {
        items(items = list.filter {
            it.contains(textState.value.text, ignoreCase = true)
        }, key = { it }) { item ->
            ExerciseColumnItem(
                item = item
            ) { navController.navigate("${Screens.HandleExerciseScreen.name}/${date}/${item}") }
        }
    }

}

@Composable
fun ExerciseColumnItem(item: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
    ) {
        Text(
            text = item,
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        Divider()
    }
}

@Composable
fun SearchTextField(
    state: MutableState<TextFieldValue>,
    placeHolder: String
) {

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    TextField(
        value = state.value,
        onValueChange = { value -> state.value = value },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        placeholder = { Text(text = placeHolder) },
        singleLine = true,
        textStyle = MaterialTheme.typography.titleMedium,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            cursorColor = colorResource(id = R.color.secondary_color),
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            disabledIndicatorColor = Color.Black
        ),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Gray
            )
        },
        shape = RectangleShape
    )
}