# Contributing

If you wish to contribute to icampuspass-kmp,
feel free to fork the repository and submit a pull request.

## Kotlin Multiplatform

This is a Kotlin Multiplatform project targeting Android, iOS.

* [/androidApp](./androidApp/src/main) contains Android applications.
  Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your Android app.
* [/shared](./shared/src) is for code that will be shared across your Compose
  Multiplatform applications between all targets.
  The most important subfolder is [commonMain](./shared/src/commonMain/kotlin).
  If preferred, you can add code to the platform-specific folders here too.
  It contains several subfolders:
  - [commonMain](./shared/src/commonMain/kotlin) is for code that’s common for
    all targets.
  - Other folders are for Kotlin code that will be compiled for only the
    platform indicated in the folder name.
    For example,
    if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./shared/src/iosMain/kotlin) folder would be the right place
    for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part,
    the [jvmMain](./shared/src/jvmMain/kotlin) folder is the appropriate
    location.
* [/iosApp](./iosApp/iosApp) contains iOS applications.
  Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app.
  This is also where you should add SwiftUI code for your project.

Learn more about
[Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html).
