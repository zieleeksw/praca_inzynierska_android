package com.example.praca_inzynierska.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.components.home.components.CustomTopAppBar

@Composable
fun AddPostScreen(
    navController: NavHostController,
    onAddPost: (String, Uri?) -> Unit
) {
    var postText by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val primaryColor = colorResource(id = R.color.primary_color)
    val pickImage =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            selectedImageUri = uri
        }
    Scaffold(
        topBar = { CustomTopAppBar(navController = navController, text = "Add a post!") },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            ) {
                Button(
                    contentPadding = PaddingValues(),
                    onClick = { pickImage.launch("image/*") },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(56.dp)
                            .border(
                                1.dp,
                                color = primaryColor,
                                shape = RoundedCornerShape(16.dp),
                            )
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(16.dp),
                            )
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Add photo",
                            color = primaryColor
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    contentPadding = PaddingValues(),
                    onClick = {
                        onAddPost(postText, selectedImageUri)
                        postText = ""
                        selectedImageUri = null
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
                value = postText,
                onValueChange = { postText = it },
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
            selectedImageUri?.let { uri ->
                Image(
                    painter = rememberAsyncImagePainter(model = uri),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(8.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun PostScreenPreview() {
    val navController = rememberNavController()
    AddPostScreen(navController = navController,
        onAddPost = { _, _ -> })
}