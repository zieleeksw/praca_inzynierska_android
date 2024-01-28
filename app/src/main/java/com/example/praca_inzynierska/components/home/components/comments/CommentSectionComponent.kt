package com.example.praca_inzynierska.components.home.components.comments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.data.Comment
import com.example.praca_inzynierska.view.models.CommentsScreenViewModel

@Composable
fun CommentSectionComponent(
    comments: List<Comment>,
    viewModel: CommentsScreenViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        LazyColumn {
            items(comments) { comment ->
                CommentItemComponent(
                    comment = comment,
                    viewModel
                )
            }
        }
    }
}