plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.keepcoding.dbandroidavanzado"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.keepcoding.dbandroidavanzado"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.coil)
    implementation (libs.material.v1110)
    implementation(libs.retrofit)
    implementation (libs.converter.scalars)
    implementation(libs.squareup.moshi.kotlin)
    implementation(libs.converter.moshi)
    implementation(libs.squareup.moshi.kotlin)
    implementation(libs.logging.interceptor)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.espresso.core)
    kapt(libs.androidx.room.compiler)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation("com.squareup.okhttp3:mockwebserver:4.2.1")
    testImplementation("com.google.truth:truth:1.0")

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}