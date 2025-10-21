package io.github.sadeghit.loginregister.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sadeghit.loginregister.data.model.Post
import io.github.sadeghit.loginregister.data.repository.PostsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostsRepository
) : ViewModel() {

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        setDataPosts()
    }

    private fun setDataPosts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getPosts()
                    .onSuccess { _posts.value = it }
                    .onFailure { e ->
                        e.printStackTrace()
                    }
            } finally {
                _isLoading.value = false
            }
        }
    }
}
