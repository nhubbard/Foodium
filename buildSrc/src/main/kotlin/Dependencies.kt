/*
 * MIT License
 *
 * Copyright (c) 2020 Shreyas Patil
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

object Testing {
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2"
    const val room = "androidx.room:room-testing:2.4.0-beta01"
    const val jUnit = "junit:junit:4.13.2"
    const val extJUnit = "androidx.test.ext:junit:1.1.2"
    const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    const val okHttp = "com.squareup.okhttp3:mockwebserver:4.9.2"
    const val core = "androidx.arch.core:core-testing:2.1.0"
    const val truth = "com.google.truth:truth:1.1.3"
}

object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.31"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31"
    const val gradle = "com.android.tools.build:gradle:7.0.3"
    const val materialDesign = "com.google.android.material:material:1.4.0"
    const val materialDialog = "dev.shreyaspatil.MaterialDialog:MaterialDialog:2.2.2"
    const val coil = "io.coil-kt:coil:1.4.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:2.4.0"
    const val okhttp = "com.squareup.okhttp3:okhttp:4.9.0"
}

object Koin {
    const val core = "io.insert-koin:koin-core:3.1.3"
    const val android = "io.insert-koin:koin-android:3.1.3"
}

object Moshi {
    const val kotlin = "com.squareup.moshi:moshi-kotlin:1.12.0"
    const val retrofitConverter = "com.squareup.retrofit2:converter-moshi:2.9.0"
}

object Serialization {
    const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1"
    const val converter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
}

object MoshiX {
    const val kspCodeGen = "dev.zacsweers.moshix:moshi-ksp:0.14.1"
}

object Coroutines {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2"
}

object Android {
    const val appcompat = "androidx.appcompat:appcompat:1.3.1"
    const val activityKtx = "androidx.activity:activity-ktx:1.4.0"
    const val coreKtx = "androidx.core:core-ktx:1.7.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.1"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
}

object Room {
    const val compiler = "androidx.room:room-compiler:2.4.0-beta01"
    const val ktx = "androidx.room:room-ktx:2.4.0-beta01"
    const val runtime = "androidx.room:room-runtime:2.4.0-beta01"
}