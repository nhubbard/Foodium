package dev.shreyaspatil.foodium.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.lifecycleScope
import dev.shreyaspatil.foodium.R
import dev.shreyaspatil.foodium.databinding.ActivityMainBinding
import dev.shreyaspatil.foodium.model.Post
import dev.shreyaspatil.foodium.ui.details.PostDetailsActivity
import dev.shreyaspatil.foodium.ui.main.adapter.PostListAdapter
import dev.shreyaspatil.foodium.utils.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    // This converts the list of Post objects from the database to the cards shown on screen.
    private val mAdapter = PostListAdapter(this::onItemClicked)
    // This monitors the network state.
    private val net = NetworkUtils()
    // This is the magic gluing this code to the view file.
    private val binding by viewBinding(ActivityMainBinding::inflate)
    // This is the magic that loads the Posts in the background without crashing the app.
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)  // Set AppTheme before setting content view.
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.run {
            postsRecyclerView.adapter = mAdapter
            swipeRefreshLayout.setOnRefreshListener { viewModel.getPosts() }
        }
    }

    override fun onStart() {
        super.onStart()
        observePosts()
        handleNetworkChanges()
    }

    private fun observePosts() {
        lifecycleScope.launchWhenStarted {
            viewModel.posts.collect { state ->
                when (state) {
                    is State.Loading -> showLoading(true)
                    is State.Success -> {
                        if (state.data.isNotEmpty()) {
                            mAdapter.submitList(state.data.toMutableList())
                            showLoading(false)
                        } else {
                            showToast("state.data.isNotEmpty() == false")
                        }
                    }
                    is State.Error -> {
                        showToast(state.message)
                        showLoading(false)
                    }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = isLoading
    }

    /**
     * Observe network changes i.e. Internet Connectivity
     */
    private fun handleNetworkChanges() {
        lifecycleScope.launchWhenStarted {
            net.getState(applicationContext).collect { isConnected ->
                if (!isConnected) {
                    binding.textViewNetworkStatus.text =
                        getString(R.string.text_no_connectivity)
                    binding.networkStatusLayout.apply {
                        show()
                        setBackgroundColor(getColorRes(R.color.colorStatusNotConnected))
                    }
                } else {
                    if (mAdapter.itemCount == 0) viewModel.getPosts()
                    binding.textViewNetworkStatus.text = getString(R.string.text_connectivity)
                    binding.networkStatusLayout.apply {
                        setBackgroundColor(getColorRes(R.color.colorStatusConnected))
                        animate {
                            alpha(1f)
                            startDelay = ANIMATION_DURATION
                            duration = ANIMATION_DURATION
                            onEnd {
                                hide()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_theme -> {
            // Change UI mode.
            AppCompatDelegate.setDefaultNightMode(uiMode())
            true
        }
        else -> true
    }

    override fun onBackPressed() {
        dialog {
            title = getString(R.string.exit_dialog_title)
            setMessage(getString(R.string.exit_dialog_message))
            setPositiveButton(getString(R.string.option_yes)) { dialogInterface, _ ->
                dialogInterface.dismiss()
                super.onBackPressed()
            }
            setNegativeButton(getString(R.string.option_no)) { dialogInterface, _ -> dialogInterface.dismiss() }
        }
    }

    private fun onItemClicked(post: Post, imageView: ImageView) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            imageView,
            imageView.transitionName
        )
        val postId = post.id ?: run {
            showToast("Unable to launch details")
            return
        }
        val intent = PostDetailsActivity.getStartIntent(this, postId)
        startActivity(intent, options.toBundle())
    }

    companion object {
        const val ANIMATION_DURATION = 1000.toLong()
    }
}
