package dev.shreyaspatil.foodium

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate.*
import dev.shreyaspatil.foodium.di.databaseModule
import dev.shreyaspatil.foodium.di.networkModule
import dev.shreyaspatil.foodium.di.viewModelModule
import dev.shreyaspatil.foodium.utils.isNight
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@ExperimentalCoroutinesApi
class FoodiumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(networkModule, databaseModule, viewModelModule)
        }
        // Get UI mode and set
        setDefaultNightMode(if (isNight()) MODE_NIGHT_YES else MODE_NIGHT_NO)
    }
}