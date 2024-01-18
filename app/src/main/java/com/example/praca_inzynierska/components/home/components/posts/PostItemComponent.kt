package com.example.praca_inzynierska.components.home.components.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.components.home.components.TimestampWithDeleteComponent
import com.example.praca_inzynierska.data.Post
import com.example.praca_inzynierska.screens.Screens
import com.example.praca_inzynierska.view.models.post.HomeScreenViewModel

@Composable
fun PostItemComponent(
    navController: NavHostController,
    post: Post,
    viewModel: HomeScreenViewModel
) {

    val authorText = getAuthorText(post)

    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(color = Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = authorText,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TimestampWithDeleteComponent(
                text = post.timestamp,
                deleteButton = post.authorId == Global.currentUserId,
                deleteString = "post",
                onConfirm = { viewModel.deletePost(post.id) },
                buttonColor = colorResource(id = R.color.primary_color)
            )
        }
        Text(
            text = post.content,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(50.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FollowButtonComponent(post = post)
            Spacer(Modifier.width(8.dp))
            Button(
                contentPadding = PaddingValues(),
                onClick = { navController.navigate("${Screens.CommentsScreen.name}/${post.id}") },
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .fillMaxHeight(),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .widthIn(240.dp)
                        .heightIn(50.dp)
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
                        text = "Comments!",
                        color = colorResource(id = R.color.primary_color)
                    )
                }
            }
        }
    }
}

fun getAuthorText(post: Post): String {
    return if (isPostOwner(post)) {
        "${post.author} (You)"
    } else {
        post.author
    }
}

fun isPostOwner(post: Post): Boolean {
    return post.authorId == Global.currentUserId
}