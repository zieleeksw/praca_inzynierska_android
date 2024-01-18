package com.example.praca_inzynierska.components.home.components.comments

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.components.home.components.TimestampWithDeleteComponent
import com.example.praca_inzynierska.data.Comment
import com.example.praca_inzynierska.screens.Screens
import com.example.praca_inzynierska.view.models.comments.DeleteCommentViewModel

@Composable
fun CommentItemComponent(
    navController: NavHostController,
    postId: Long,
    comment: Comment
) {

    val deleteCommentViewModel = DeleteCommentViewModel()
    val isCommentOwner = comment.authorId == Global.currentUserId
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        deleteCommentViewModel.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {
                    navController.popBackStack()
                    Toast.makeText(
                        context, "Successfully deleted comment",
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

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.primary_color),
            contentColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                .background(color = colorResource(id = R.color.primary_color))
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 4.dp, end = 4.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (isCommentOwner) {
                        "${comment.username} (You)"
                    } else {
                        comment.username
                    },
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
                TimestampWithDeleteComponent(
                    text = comment.timestamp,
                    deleteButton = comment.authorId == Global.currentUserId,
                    onConfirm = {
                        performDeleteAction(
                            deleteCommentViewModel,
                            navController,
                            postId,
                            comment.id
                        )
                    },
                    buttonColor = Color.White
                )
            }
            Text(text = comment.content)
        }
    }
}

fun performDeleteAction(
    deleteViewModel: DeleteCommentViewModel,
    navController: NavHostController,
    postId: Long,
    commentId: Long
) {
    deleteViewModel.onClick(commentId)
    navController.navigate("${Screens.CommentsScreen.name}/${postId}")
}
