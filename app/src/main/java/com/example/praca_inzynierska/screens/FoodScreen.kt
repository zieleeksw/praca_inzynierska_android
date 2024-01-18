package com.example.praca_inzynierska.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.components.food.components.test.ExpandableCard1
import com.example.praca_inzynierska.components.home.components.posts.PostSectionComponent
import com.example.praca_inzynierska.enums.Meal
import com.example.praca_inzynierska.view.models.food.FetchFoodViewModel
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodScreen(
    navController: NavHostController
) {

    var dateResult by remember { mutableStateOf(convertMillisToDateString(System.currentTimeMillis())) }
    var openDialog by remember { mutableStateOf(false) }
    val fetchFoodViewModel = FetchFoodViewModel(dateResult)


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Control your calories!",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.primary_color),
                    actionIconContentColor = Color.White
                )
            )
        }) {
        Column {

            when {

                fetchFoodViewModel.foodState.value.loading ->
                    CircularProgressIndicator()

                fetchFoodViewModel.foodState.value.error != null ->
                    Text("ERROR OCCURED")

                else -> {
            Row(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth()
                    .height(54.dp)
                    .background(color = colorResource(id = R.color.primary_color)),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .width(120.dp),
                    value = dateResult, onValueChange = {},
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = colorResource(id = R.color.primary_color),
                    ),
                )
                Button(contentPadding = PaddingValues(),
                    modifier = Modifier
                        .heightIn(60.dp)
                        .width(50.dp)
                        .background(color = colorResource(id = R.color.primary_color)),
                    onClick = {
                        openDialog = true
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .background(colorResource(id = R.color.primary_color))
                            .height(60.dp)
                            .width(60.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            Icons.Default.DateRange, contentDescription = "Date icon",
                            modifier = Modifier
                                .width(50.dp)
                                .height(34.dp),
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }
            }

            ExpandableCard1(
                cardTitle = Meal.BREAKFAST.displayName,
                fetchFoodViewModel.foodState.value.list,
                date = dateResult,
                navController
            )
            ExpandableCard1(
                cardTitle = Meal.DINNER.displayName,
                fetchFoodViewModel.foodState.value.list,
                date = dateResult,
                navController
            )
            ExpandableCard1(
                cardTitle = Meal.LUNCH.displayName,
                fetchFoodViewModel.foodState.value.list,
                date = dateResult,
                navController
            )
            if (openDialog) {
                val datePickerState = rememberDatePickerState()
                val confirmEnabled = derivedStateOf { datePickerState.selectedDateMillis != null }
                DatePickerDialog(
                    onDismissRequest = { openDialog = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                openDialog = false
                                var date = "No selection"
                                if (datePickerState.selectedDateMillis != null) {
                                    date =
                                        convertMillisToDateString(datePickerState.selectedDateMillis!!)

                                }
                                dateResult = date
                            },
                            enabled = confirmEnabled.value
                        ) {
                            Text(text = "Okay")
                        }
                    }) {
                    DatePicker(
                        state = datePickerState,
                        colors = DatePickerDefaults.colors(
                            dayContentColor = colorResource(id = R.color.primary_color),
                            titleContentColor = colorResource(id = R.color.primary_color),
                            headlineContentColor = colorResource(id = R.color.primary_color),
                            weekdayContentColor = colorResource(id = R.color.primary_color),
                            subheadContentColor = colorResource(id = R.color.primary_color)
                        )
                    )
                }
            }

                }
            }
        }
    }
}

fun convertMillisToDateString(dateMillis: Long): String {
    val date = Date(dateMillis)
    val format = SimpleDateFormat("yyyy-MM-dd")
    return format.format(date)
}

@Composable
@Preview(showBackground = true)
fun FoodScreenPreview() {
    FoodScreen(rememberNavController())
}