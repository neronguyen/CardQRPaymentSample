plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.database)
    implementation(projects.core.sdk)
    implementation(projects.core.network)

    implementation(libs.kotlinx.coroutines.core)
}

kotlin {
    jvmToolchain(25)
    compilerOptions {
        optIn.add("kotlin.uuid.ExperimentalUuidApi")
    }
}
