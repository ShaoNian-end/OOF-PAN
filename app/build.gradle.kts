plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.miuix.demo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.miuix.demo"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Miuix 核心 UI 组件库
    implementation("top.yukonga.miuix.kmp:miuix-ui-android:0.9.3")

    // Miuix Preference 组件
    implementation("top.yukonga.miuix.kmp:miuix-preference-android:0.9.3")

    // Miuix 扩展图标
    implementation("top.yukonga.miuix.kmp:miuix-icons-android:0.9.3")

    // Miuix 模糊效果
    implementation("top.yukonga.miuix.kmp:miuix-blur-android:0.9.3")

    // Jetpack Compose
    implementation(platform("androidx.compose:compose-bom:2024.12.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")

    // Activity
    implementation("androidx.activity:activity-compose:1.9.3")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.8.5")

    // Debug
    debugImplementation("androidx.compose.ui:ui-tooling")
}