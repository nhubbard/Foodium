package dev.shreyaspatil.foodium.ui.main.viewholder

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dev.shreyaspatil.foodium.R
import dev.shreyaspatil.foodium.databinding.ItemPostBinding
import dev.shreyaspatil.foodium.model.Post
import dev.shreyaspatil.foodium.ui.main.adapter.PostListAdapter

/**
 * [RecyclerView.ViewHolder] implementation to inflate View for RecyclerView.
 * See [PostListAdapter].
 */
class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post, onItemClicked: (Post, ImageView) -> Unit) = binding.apply {
        postTitle.text = post.title
        postAuthor.text = post.author
        imageView.load(post.imageUrl) {
            placeholder(R.drawable.ic_photo)
            error(R.drawable.ic_broken_image)
        }
        root.setOnClickListener {
            onItemClicked(post, binding.imageView)
        }
    }
}
