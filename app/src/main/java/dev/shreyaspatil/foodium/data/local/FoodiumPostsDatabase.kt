package dev.shreyaspatil.foodium.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import dev.shreyaspatil.foodium.data.local.dao.PostsDao
import dev.shreyaspatil.foodium.model.Post
import dev.shreyaspatil.foodium.utils.database

/**
 * Abstract Foodium database.
 * It provides DAO [PostsDao] by using method [getPostsDao].
 */
@Database(entities = [Post::class], version = DatabaseMigrations.DB_VERSION)
abstract class FoodiumPostsDatabase : RoomDatabase() {
    /**
     * @return [PostsDao] Foodium Posts Data Access Object.
     */
    abstract fun getPostsDao(): PostsDao

    companion object {
        private const val DB_NAME = "foodium_database"

        @Volatile
        private var INSTANCE: FoodiumPostsDatabase? = null

        fun getInstance(context: Context): FoodiumPostsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = database<FoodiumPostsDatabase>(context.applicationContext, DB_NAME) {
                    addMigrations(*DatabaseMigrations.MIGRATIONS)
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}