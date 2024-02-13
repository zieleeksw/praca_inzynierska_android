package com.example.praca_inzynierska.forum.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import com.example.praca_inzynierska.commons.objects.Global
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.objects.Ui
import com.example.praca_inzynierska.commons.components.TimestampWithDeleteComponent
import com.example.praca_inzynierska.forum.data.Post
import com.example.praca_inzynierska.forum.vm.HomeScreenViewModel
import com.example.praca_inzynierska.commons.screens.Screens

@Composable
fun PostItemComponent(
    navController: NavHostController,
    post: Post,
    viewModel: HomeScreenViewModel
) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = Ui.DEFAULT_CARD_ELEVATION
        ),
        shape = Ui.DEFAULT_ROUNDED_CORNER_SHAPE
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp),
            ) {
                Text(
                    text = viewModel.getAuthorText(post = post),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                TimestampWithDeleteComponent(
                    text = post.timestamp,
                    deleteButton = post.authorId == Global.currentUserId,
                    deleteString = "post",
                    onConfirm = { viewModel.deletePost(post.id) }
                )
            }
            Text(
                text = post.content,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 4.dp),
            )
            Divider(
                thickness = 1.dp,
                color = colorResource(id = R.color.secondary_color),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FollowComponent(
                    modifier = Modifier.weight(1f),
                    post = post,
                    viewModel
                )
                CommentComponent(
                    modifier = Modifier.weight(1f)
                ) { navController.navigate("${Screens.CommentsScreen.name}/${post.id}") }
            }
        }
    }
}