package com.example.praca_inzynierska.forum.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.commons.objects.Global.currentUserId
import com.example.praca_inzynierska.commons.objects.Global.token
import com.example.praca_inzynierska.commons.states.ResourceState
import com.example.praca_inzynierska.forum.data.Post
import com.example.praca_inzynierska.forum.services.postService
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val _postState = mutableStateOf(ResourceState<Post>())
    val postState: State<ResourceState<Post>> = _postState

    private val _selectedFilter = mutableStateOf<String?>(null)


    val filteredPosts: State<List<Post>> = derivedStateOf {
        when (_selectedFilter.value) {
            "Followed" -> postState.value.list.filter { it.followers.contains(currentUserId) }
            "Newest" -> postState.value.list
            "Oldest" -> postState.value.list.asReversed()
            "Mine" -> postState.value.list.filter { it.authorId == currentUserId }
            else -> postState.value.list
        }
    }

    fun onFilterChanged(filter: String) {
        _selectedFilter.value = filter
    }

    fun deletePost(postId: Long) {
        viewModelScope.launch {
            try {
                postService.deletePost("Bearer $token", postId)
                fetchPosts()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                val response = postService.fetchAllPosts("Bearer $token")
                _postState.value = _postState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _postState.value = _postState.value.copy(
                    loading = false,
                    error = "Error fetching Posts ${e.message}"
                )
            }
        }
    }

    fun onFollowClick(postId: Long) {
        viewModelScope.launch {
            try {
                val response = postService.followPost("Bearer $token", currentUserId, postId)
                if (response.isSuccessful) {
                    updatePostFollowingState(postId)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun updatePostFollowingState(postId: Long) {
        _postState.value = _postState.value.let { currentState ->
            val newList = currentState.list.map { post ->
                if (post.id == postId) {
                    post.copy(
                        followers = if (post.followers.contains(currentUserId))
                            post.followers - currentUserId else post.followers + currentUserId
                    )
                } else post
            }
            currentState.copy(list = newList)
        }
    }

    fun getAuthorText(post: Post): String {
        return if (post.authorId == currentUserId) {
            "${post.author} (You)"
        } else {
            post.author
        }
    }
}