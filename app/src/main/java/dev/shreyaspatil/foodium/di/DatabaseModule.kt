package dev.shreyaspatil.foodium.di

import dev.shreyaspatil.foodium.data.local.FoodiumPostsDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { FoodiumPostsDatabase.getInstance(androidApplication()) }
    single { get<FoodiumPostsDatabase>().getPostsDao() }
}