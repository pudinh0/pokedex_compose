plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.0.21"
}

android {
    namespace = "adel.mendez.pokedex_compose"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "adel.mendez.pokedex_compose"
        minSdk = 27
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)

    // 1. Cambiar a datastore-preferences (para clave-valor) o conservar si usas Protos pero con su artefacto correcto
    implementation("androidx.datastore:datastore-preferences:1.1.1") // Versión estable recomendada

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1") // Actualizado a una versión estándar compatible con Kotlin 2.0+

    // 2. Corregir el error tipográfico (serializatrion -> serialization)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}