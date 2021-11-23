package dev.shreyaspatil.foodium.di

import dev.shreyaspatil.foodium.data.remote.api.FoodiumService
import dev.shreyaspatil.foodium.data.repository.DefaultPostRepository
import dev.shreyaspatil.foodium.utils.retrofit
import dev.shreyaspatil.foodium.utils.serialization
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.dsl.module

@ExperimentalSerializationApi
val networkModule = module {
    single {
        retrofit<FoodiumService> {
            baseUrl(FoodiumService.FOODIUM_API_URL)
            serialization()
        }
    }
    single { DefaultPostRepository(get(), get()) }
}