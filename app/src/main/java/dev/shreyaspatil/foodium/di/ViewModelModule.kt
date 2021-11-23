package dev.shreyaspatil.foodium.di

import dev.shreyaspatil.foodium.ui.details.PostDetailsViewModel
import dev.shreyaspatil.foodium.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { PostDetailsViewModel(get()) }
}
