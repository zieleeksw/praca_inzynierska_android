package com.example.praca_inzynierska.forum.components.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUpOffAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praca_inzynierska.commons.objects.Global
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.forum.data.Post
import com.example.praca_inzynierska.forum.vm.HomeScreenViewModel

@Composable
fun FollowComponent(
    modifier: Modifier,
    post: Post,
    viewModel: HomeScreenViewModel
) {
    val isFollowed = post.followers.contains(Global.currentUserId)

    Column(
        modifier = modifier
            .clickable { viewModel.onFollowClick(post.id) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.ThumbUpOffAlt,
            contentDescription = null,
            tint = if (isFollowed) colorResource(id = R.color.secondary_color) else Color.Gray,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = if (isFollowed) "Followed!" else "Follow!",
            color = if (isFollowed) colorResource(id = R.color.secondary_color) else Color.Gray,
            fontSize = 12.sp
        )
    }
}