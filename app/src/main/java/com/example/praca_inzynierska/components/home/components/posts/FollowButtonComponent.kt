package com.example.praca_inzynierska.components.home.components.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.data.Post
import com.example.praca_inzynierska.view.models.post.FollowClickViewModel

@Composable
fun FollowButtonComponent(
    post: Post
) {

    val isFollowed = post.followers.contains(Global.currentUserId)
    val followClickViewModel = FollowClickViewModel(post.id, isFollowed)

    Button(
        contentPadding = PaddingValues(),
        onClick = { followClickViewModel.onFollowClick() },
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
                    color = getPostColor(followClickViewModel = followClickViewModel, post = post),
                    shape = RoundedCornerShape(4.dp),
                )
        ) {
            Text(text = getFollowText(followClickViewModel = followClickViewModel, post = post))
        }
    }
}

@Composable
fun getPostColor(followClickViewModel: FollowClickViewModel, post: Post): Color {
    return when {
        followClickViewModel.id == post.id && followClickViewModel.state -> colorResource(id = R.color.secondary_color)
        else -> colorResource(id = R.color.primary_color)
    }
}

fun getFollowText(followClickViewModel: FollowClickViewModel, post: Post): String {
    return if (followClickViewModel.id == post.id && followClickViewModel.state) {
        "Followed!"
    } else {
        "Follow!"
    }
}