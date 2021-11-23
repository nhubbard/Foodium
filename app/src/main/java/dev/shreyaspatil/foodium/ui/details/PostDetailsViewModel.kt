package dev.shreyaspatil.foodium.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dev.shreyaspatil.foodium.data.repository.DefaultPostRepository

/**
 * ViewModel for [PostDetailsActivity]
 */
class PostDetailsViewModel(private val postsRepository: DefaultPostRepository) : ViewModel() {
    fun getPost(id: Int) = postsRepository.getPostById(id).asLiveData()
}