package dev.shreyaspatil.foodium.ui.details

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import coil.load
import dev.shreyaspatil.foodium.R
import dev.shreyaspatil.foodium.databinding.ActivityPostDetailsBinding
import dev.shreyaspatil.foodium.model.Post
import dev.shreyaspatil.foodium.utils.intent
import dev.shreyaspatil.foodium.utils.startChooser
import dev.shreyaspatil.foodium.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostDetailsActivity : AppCompatActivity() {
    private lateinit var post: Post
    private val binding by viewBinding(ActivityPostDetailsBinding::inflate)
    private val viewModel: PostDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val postId = intent.extras?.getInt(POST_ID)
            ?: throw IllegalArgumentException("postId must be non-null")
        initPost(postId)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return true
    }

    private fun initPost(postId: Int) {
        viewModel.getPost(postId).observe(this) { post ->
            this@PostDetailsActivity.post = post
            binding.postContent.apply {
                postTitle.text = post.title
                postAuthor.text = post.author
                postBody.text = post.body
            }
            binding.imageView.load(post.imageUrl)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            supportFinishAfterTransition()
            true
        }
        R.id.action_share -> {
            startChooser {
                setType("text/plain")
                setText(getString(R.string.share_message, post.title, post.author))
            }
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    companion object {
        const val POST_ID = "postId"

        fun getStartIntent(context: Context, postId: Int) = intent<PostDetailsActivity>(context) {
            putExtra(POST_ID, postId)
        }
    }
}