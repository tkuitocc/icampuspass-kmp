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
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    listOf(
        iosArm64(),
        iosSimulatorArm64(),
        iosX64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"

            // Changed from true to false, required by SQLDelight
            isStatic = false
        }
    }
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
    sourceSets {
        all {
            // Required by KMP-NativeCoroutines
            languageSettings.optIn(annotationName = "kotlin.experimental.ExperimentalObjCName")

            // Required by KMP-ObservableViewModel
            languageSettings.optIn(annotationName = "kotlinx.cinterop.ExperimentalForeignApi")
        }
        androidMain.dependencies {
            implementation(libs.koin.androidx.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.sqldelight.android)
        }
        commonMain.dependencies {
            // put your Multiplatform dependencies here
            api(libs.kmp.observable.viewmodel)
            implementation(libs.koin.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.sqldelight.runtime)

            api(libs.kmp.observable.viewmodel)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.sqldelight.native)
        }
    }
}

android {
    namespace = "com.itocc.icampuspass.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

sqldelight {
    databases {
        create(name = "Database") {
            packageName.set("com.itocc.icampuspass.models.database")
            generateAsync.set(true)
        }
    }
}
