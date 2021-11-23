import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization") version "1.5.31"
    id("com.google.devtools.ksp") version "1.5.31-1.0.0"
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "dev.shreyaspatil.foodium"
        minSdk = 26
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding {
            isEnabled = true
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    namespace = "dev.shreyaspatil.foodium"
}

ksp {
    // Room configuration
    arg("room.schemaLocation", "$projectDir/schemas")
    arg("room.incremental", "true")
    arg("room.expandProjection", "true")
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Kotlin
    implementation(Dependencies.kotlin)

    // Coroutines
    implementation(Coroutines.core)
    implementation(Coroutines.android)

    // Android
    implementation(Android.appcompat)
    implementation(Android.coreKtx)
    implementation(Android.activityKtx)
    implementation(Android.constraintLayout)
    implementation(Android.swipeRefreshLayout)

    // Architecture Components
    implementation(Dependencies.viewModel)
    implementation(Dependencies.liveData)

    // Room components
    implementation(Room.runtime)
    ksp(Room.compiler)
    implementation(Room.ktx)

    // Material Design
    implementation(Dependencies.materialDesign)
    implementation(Dependencies.materialDialog)

    // Coil
    implementation(Dependencies.coil)

    // Retrofit
    implementation(Dependencies.retrofit)

    // Moshi
    implementation(Moshi.kotlin)
    implementation(Moshi.retrofitConverter)
    ksp(MoshiX.kspCodeGen)

    // KotlinX Serialization
    implementation(Dependencies.okhttp)
    implementation(Serialization.json)
    implementation(Serialization.converter)

    // Testing
    testImplementation(Testing.core)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.room)
    testImplementation(Testing.okHttp)
    testImplementation(Testing.jUnit)
    testImplementation(Testing.truth)
    androidTestImplementation(Testing.extJUnit)
    androidTestImplementation(Testing.espresso)

    // Koin
    implementation(Koin.core)
    implementation(Koin.android)
}
