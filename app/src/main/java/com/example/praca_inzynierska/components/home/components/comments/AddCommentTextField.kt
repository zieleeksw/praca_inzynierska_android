package com.example.praca_inzynierska.components.home.components.comments

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.view.models.CommentsScreenViewModel

@Composable
fun AddCommentTextField(
    postId: Long,
    viewModel: CommentsScreenViewModel
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {
                    Toast.makeText(
                        context, "Successfully added new comment",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is ValidationEvent.BadCredentials ->
                    Toast.makeText(
                        context, "You have enter correct comment content",
                        Toast.LENGTH_LONG
                    ).show()

                is ValidationEvent.Failure ->
                    Toast.makeText(
                        context, "Somethig went wrong. Try again later",
                        Toast.LENGTH_LONG
                    ).show()
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        OutlinedTextField(
            value = viewModel.addCommentState,
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