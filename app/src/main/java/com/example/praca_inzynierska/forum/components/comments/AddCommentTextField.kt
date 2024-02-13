package com.example.praca_inzynierska.forum.components.comments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.forum.vm.CommentsScreenViewModel

@Composable
fun AddCommentTextField(
    postId: Long,
    viewModel: CommentsScreenViewModel
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.light_gray))
            .padding(8.dp),
    ) {
        OutlinedTextField(
            value = viewModel.createCommentState.content,
            isError = viewModel.createCommentState.contentError != null,
            onValueChange = { viewModel.onContentChanged(it) },
            placeholder = { Text("Add a comment...") },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = colorResource(id = R.color.primary_color),
                focusedBorderColor = colorResource(id = R.color.secondary_color),
                focusedLabelColor = colorResource(id = R.color.secondary_color),
                cursorColor = colorResource(id = R.color.secondary_color)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
            trailingIcon = {
                IconButton(onClick = {
                    viewModel.addComment(postId)
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Send,
                        contentDescription = null,
                        tint = colorResource(id = R.color.primary_color)
                    )
                }
            }
        )
    }
}