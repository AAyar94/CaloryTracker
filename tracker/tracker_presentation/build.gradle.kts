plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = "com.aayar94.tracker_presentation"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.coreui))
    implementation(project(Modules.trackerDomain))

    implementation(Coil.coilCompose)
}