package com.example.praca_inzynierska.components.home.components.posts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.ThumbUpOffAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.components.home.components.TimestampWithDeleteComponent
import com.example.praca_inzynierska.data.Post
import com.example.praca_inzynierska.screens.Screens
import com.example.praca_inzynierska.view.models.post.FollowClickViewModel
import com.example.praca_inzynierska.view.models.post.HomeScreenViewModel

@Composable
fun PostItemComponent(
    navController: NavHostController,
    post: Post,
    viewModel: HomeScreenViewModel
) {

    val authorText = getAuthorText(post)

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
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
                    buttonColor = Color.Gray
                )
            }
            Text(
                text = post.content,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Divider(
                thickness = 1.dp,
                color = colorResource(id = R.color.secondary_color),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FollowComponent(
                    modifier = Modifier.weight(1f),
                    post = post,
                    onUpdateFollowers = {
                        if (post.followers.contains(Global.currentUserId)) {
                            post.followers = post.followers - Global.currentUserId
                        } else {
                            post.followers = post.followers + Global.currentUserId
                        }
                    }
                )
                CommentComponent(
                    modifier = Modifier.weight(1f)
                ) { navController.navigate("${Screens.CommentsScreen.name}/${post.id}") }
            }
        }
    }
}


@Composable
fun FollowComponent(modifier: Modifier, post: Post, onUpdateFollowers: () -> Unit) {

    val isFollowed = post.followers.contains(Global.currentUserId)
    val followClickViewModel = FollowClickViewModel(post.id, isFollowed)

    Column(
        modifier = modifier
            .clickable {
                followClickViewModel.onFollowClick()
                onUpdateFollowers()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.ThumbUpOffAlt,
            contentDescription = null,
            tint = getPostColor(followClickViewModel = followClickViewModel, post = post),
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = getFollowText(followClickViewModel = followClickViewModel, post = post),
            color = getPostColor(followClickViewModel = followClickViewModel, post = post),
            fontSize = 12.sp
        )
    }
}

@Composable
fun CommentComponent(modifier: Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Message,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "Comment", color = Color.Gray, fontSize = 12.sp
        )
    }
}

@Composable
fun getPostColor(followClickViewModel: FollowClickViewModel, post: Post): Color {
    return when {
        followClickViewModel.id == post.id && followClickViewModel.state -> colorResource(id = R.color.secondary_color)
        else -> Color.Gray
    }
}

fun getFollowText(followClickViewModel: FollowClickViewModel, post: Post): String {
    return if (followClickViewModel.id == post.id && followClickViewModel.state) {
        "Followed!"
    } else {
        "Follow!"
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