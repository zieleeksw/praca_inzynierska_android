package com.example.praca_inzynierska.components.home.components.posts

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.screens.Screens

@Composable
fun PostItemComponent(
    navController: NavHostController,
    author: String,
    content: String,
    timestamp: String,
    onFollowClick: () -> Unit
) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(color = Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "$author",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "$timestamp",
                fontSize = 14.sp,
                color = Color.Gray,
            )
        }
        Text(
            text = content,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
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
        Text(
            text = "Followers: 126",
            fontSize = 14.sp,
            color = Color.Gray,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(50.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                contentPadding = PaddingValues(),
                onClick = { onFollowClick() },
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .width(110.dp)
                        .height(50.dp)
                        .background(
                            color = colorResource(id = R.color.primary_color),
                            shape = RoundedCornerShape(4.dp),
                        )
                ) {
                    Text(text = "Follow")
                }
            }
            Button(
                contentPadding = PaddingValues(),
                onClick = { navController.navigate(Screens.AddCommentScreen.name) },
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .fillMaxHeight(),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .width(240.dp)
                        .height(50.dp)
                        .border(
                            1.dp,
                            color = colorResource(id = R.color.primary_color)
                        )
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(4.dp),
                        )
                ) {
                    Text(
                        text = "Add a comment!",
                        color = colorResource(id = R.color.primary_color)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PostPreview() {
    val navController = rememberNavController()
    val author = "John Doe"
    val content = "This is the post content."
    val timestamp = "2 hours ago"
    PostItemComponent(
        navController = navController,
        author = author,
        content = content,
        timestamp = timestamp,
        onFollowClick = {}
    )
}