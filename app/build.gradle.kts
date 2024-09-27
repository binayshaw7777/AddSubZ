plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
//    id("kotlin-parcelize")
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "com.binayshaw7777.addsubz"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.binayshaw7777.addsubz"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
    }
    composeCompiler {
        reportsDestination = layout.buildDirectory.dir("compose_compiler")
//        stabilityConfigurationFile = rootProject.layout.projectDirectory.file("stability_config.conf")
    }

    packaging.resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.material3)
    implementation(libs.androidx.runtime.livedata)

    //ViewModel
    implementation (libs.androidx.lifecycle.viewmodel.ktx)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.work)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.hilt.navigation.compose)

    // Datastore
    implementation(libs.androidx.datastore.preferences)

    // Compose-lint
    lintChecks(libs.compose.lint.checks)

    // Timber
    implementation (libs.timber)

    //kotlinx.collections.immutable
    implementation(libs.kotlinx.collections.immutable)

    // Coil
    implementation(libs.coil.compose)
}