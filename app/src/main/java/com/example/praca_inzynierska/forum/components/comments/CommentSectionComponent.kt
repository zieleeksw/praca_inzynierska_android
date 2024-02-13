package com.example.praca_inzynierska.forum.components.comments

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.praca_inzynierska.forum.vm.CommentsScreenViewModel

@Composable
fun CommentSectionComponent(
    viewModel: CommentsScreenViewModel,
    postId: Long
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(viewModel.commentState.value.list) { comment ->
            CommentItemComponent(
                comment = comment,
                viewModel = viewModel,
                postId = postId
            )
        }
    }
}