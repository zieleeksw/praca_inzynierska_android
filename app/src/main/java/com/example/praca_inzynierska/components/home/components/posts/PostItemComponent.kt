package com.example.praca_inzynierska.components.home.components.posts

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.components.home.components.TimestampWithDeleteComponent
import com.example.praca_inzynierska.data.Post
import com.example.praca_inzynierska.screens.Screens
import com.example.praca_inzynierska.view.models.post.DeletePostViewModel

@Composable
fun PostItemComponent(
    navController: NavHostController,
    post: Post,
) {

    val deletePostViewModel = viewModel<DeletePostViewModel>()
    val isPostOwner = post.authorId == Global.currentUserId
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        deletePostViewModel.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {
                    navController.popBackStack()
                    Toast.makeText(
                        context, "Successfully deleted post",
                        Toast.LENGTH_LONG
                    ).show()
                }

                else ->
                    Toast.makeText(
                        context, "Something went wrong. Try again later",
                        Toast.LENGTH_LONG
                    ).show()
            }
        }
    }

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
                text = if (isPostOwner) {
                    "${post.author} (You)"
                } else {
                    post.author
                },
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TimestampWithDeleteComponent(
                text = post.timestamp,
                deleteButton = post.authorId == Global.currentUserId,
                onConfirm = { performDeleteAction(deletePostViewModel, navController, post.id) },
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

fun performDeleteAction(
    deleteViewModel: DeletePostViewModel,
    navController: NavHostController,
    postId: Long
) {
    deleteViewModel.onClick(postId)
    navController.navigate(Screens.HomeScreen.name)
}