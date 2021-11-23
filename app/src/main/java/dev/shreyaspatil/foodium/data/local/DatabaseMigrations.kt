package dev.shreyaspatil.foodium.data.local

import dev.shreyaspatil.foodium.model.Post
import dev.shreyaspatil.foodium.utils.migration

object DatabaseMigrations {
    const val DB_VERSION = 2

    val MIGRATIONS = arrayOf(migration12())

    private fun migration12() = migration(1, 2) {
        it.execSQL("ALTER TABLE ${Post.TABLE_NAME} ADD COLUMN body TEXT")
    }
}