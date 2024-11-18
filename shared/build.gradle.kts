import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kmpNativeCoroutines)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.sqldelight)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    listOf(
        iosArm64(),
        iosSimulatorArm64(),
        iosX64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    sourceSets {
        all {
            // Required by KMP-NativeCoroutines
            languageSettings.optIn(annotationName = "kotlin.experimental.ExperimentalObjCName")

            // Required by KMP-ObservableViewModel
            languageSettings.optIn(annotationName = "kotlinx.cinterop.ExperimentalForeignApi")
        }
        commonMain.dependencies {
            // put your Multiplatform dependencies here
            api(libs.kmp.observable.viewmodel)
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.itocc.icampuspass.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
