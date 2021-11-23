package dev.shreyaspatil.foodium.data.repository

import androidx.annotation.MainThread
import dev.shreyaspatil.foodium.data.local.dao.PostsDao
import dev.shreyaspatil.foodium.data.remote.api.FoodiumService
import dev.shreyaspatil.foodium.model.Post
import dev.shreyaspatil.foodium.utils.networkRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Singleton repository for fetching data from remote and storing it in database
 * for offline capability. This is Single source of data.
 */
class DefaultPostRepository constructor(
    private val postsDao: PostsDao,
    private val foodiumService: FoodiumService
) : PostRepository {
    /**
     * Fetched the posts from network and stored it in database. At the end, data from persistence
     * storage is fetched and emitted.
     */
    override fun getAllPosts(): Flow<Resource<List<Post>>> =
        networkRepo(postsDao::addPosts, postsDao::getAllPosts, foodiumService::getPosts)

    /**
     * Retrieves a post with specified [postId].
     * @param postId Unique id of a [Post].
     * @return [Post] data fetched from the database.
     */
    @MainThread
    override fun getPostById(postId: Int): Flow<Post> = postsDao.getPostById(postId)
        .distinctUntilChanged()
}
