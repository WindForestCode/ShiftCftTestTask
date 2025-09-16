plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "1.9.21"
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    
}

android {
    namespace = "com.myschool.shiftcft"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.myschool.shiftcft"
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
        compose = true
    }
}

dependencies {

    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.androidx.room.ktx)
    implementation(libs.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.ui.tooling)
    ksp(libs.androidx.room.compiler)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    ksp(libs.dagger.compiler)
    implementation(libs.okhttp)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.glide)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}