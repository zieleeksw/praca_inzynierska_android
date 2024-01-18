package com.example.praca_inzynierska.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.components.home.components.CustomTopAppBar
import com.example.praca_inzynierska.view.models.post.AddPostViewModel

@Composable
fun AddPostScreen(
    navController: NavHostController,
) {

    val primaryColor = colorResource(id = R.color.primary_color)
    val viewModel = viewModel<AddPostViewModel>()
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {
                    navController.popBackStack()
                    Toast.makeText(
                        context, "Successfully added new post",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is ValidationEvent.BadCredentials ->
                    Toast.makeText(
                        context, "You have enter correct post content",
                        Toast.LENGTH_LONG
                    ).show()

                is ValidationEvent.Failure ->
                    Toast.makeText(
                        context, "Something went wrong. Try again later",
                        Toast.LENGTH_LONG
                    ).show()
            }
        }
    }

    Scaffold(
        topBar = { CustomTopAppBar(text = "Add a post!") { navController.navigate(Screens.HomeScreen.name) } },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    contentPadding = PaddingValues(),
                    onClick = {
                        viewModel.onSubmit()
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.height(56.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .background(
                                color = primaryColor,
                                shape = RoundedCornerShape(16.dp),
                            )
                    ) {
                        Text(text = "Post")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

            }
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            OutlinedTextField(
                value = viewModel.state.content,
                onValueChange = { content -> viewModel.onContentChanged(content) },
                placeholder = { Text("Text...") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    thickness = 1.dp,
                    color = colorResource(id = R.color.primary_color),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                )
            }
        }
    }
}

@Preview
@Composable
fun PostScreenPreview() {
    val navController = rememberNavController()
    AddPostScreen(navController = navController)
}