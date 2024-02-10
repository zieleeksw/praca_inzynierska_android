package com.example.praca_inzynierska.components.home.components.comments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.components.home.components.TimestampWithDeleteComponent
import com.example.praca_inzynierska.data.Comment
import com.example.praca_inzynierska.view.models.CommentsScreenViewModel

@Composable
fun CommentItemComponent(
    comment: Comment,
    viewModel: CommentsScreenViewModel
) {

    val isCommentOwner = comment.authorId == Global.currentUserId

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Gray
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                .background(color = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isCommentOwner) {
                        "${comment.username} (You)"
                    } else {
                        comment.username
                    },
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                TimestampWithDeleteComponent(
                    text = comment.timestamp,
                    deleteButton = comment.authorId == Global.currentUserId,
                    deleteString = "comment",
                    onConfirm = {
                        viewModel.deleteComment(comment.id)
                    },
                    buttonColor = Color.Gray
                )
            }
            Text(text = comment.content)
        }
    }
}

