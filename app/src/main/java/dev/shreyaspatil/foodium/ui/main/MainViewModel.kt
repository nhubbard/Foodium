package dev.shreyaspatil.foodium.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.shreyaspatil.foodium.data.repository.DefaultPostRepository
import dev.shreyaspatil.foodium.model.Post
import dev.shreyaspatil.foodium.utils.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * ViewModel for [MainActivity]
 */
class MainViewModel(private val postsRepository: DefaultPostRepository) : ViewModel() {
    private val _posts = MutableStateFlow<State<List<Post>>>(State.loading())
    val posts: StateFlow<State<List<Post>>> = _posts

    fun getPosts() {
        viewModelScope.launch {
            postsRepository.getAllPosts()
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _posts.value = state }
        }
    }
}
