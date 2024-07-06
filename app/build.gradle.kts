plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.agb.movielist"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.agb.movielist"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        buildConfig = true
        dataBinding = true
    }
    hilt {
        enableAggregatingTask = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.hilt.common)
    implementation(libs.androidx.hilt.work)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //ViewModel
    implementation (libs.androidx.lifecycle.viewmodel)
    implementation (libs.androidx.lifecycle.livedata)
    implementation (libs.androidx.lifecycle.runtime)
    implementation (libs.androidx.lifecycle.viewmodel.savedstate)
    implementation (libs.androidx.lifecycle.common.java8)
    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    // Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    // WorkManager
    implementation ("androidx.work:work-runtime-ktx:2.9.0")
    // Hilt extension for WorkManager
    implementation ("androidx.hilt:hilt-work:1.2.0")
    kapt ("androidx.hilt:hilt-compiler:1.2.0")
    kapt(libs.hilt.android.compiler)
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.gson)
    //okHttp
    implementation(libs.okhttp)
    implementation(platform(libs.okhttp.bom))
    //noinspection UseTomlInstead
    implementation("com.squareup.okhttp3:okhttp")
    //noinspection UseTomlInstead
    implementation("com.squareup.okhttp3:logging-interceptor")
    testImplementation(libs.mockwebserver)
    // Glide
    implementation(libs.glide)
    annotationProcessor(libs.compiler)
    //Room
    implementation (libs.androidx.room.runtime)
    annotationProcessor (libs.androidx.room.room.compiler)
    kapt (libs.androidx.room.room.compiler)
    implementation (libs.androidx.room.ktx)
    implementation (libs.androidx.room.guava)
    testImplementation (libs.androidx.room.testing)
    implementation (libs.androidx.room.paging)
    //KotlinX DateTime
    implementation(libs.kotlinx.datetime)
    //Paging
    implementation("androidx.paging:paging-runtime:3.3.0")

}