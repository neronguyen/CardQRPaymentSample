plugins {
    alias(libs.plugins.kotlin.jvm)
}

kotlin {
    jvmToolchain(25)
    compilerOptions {
        optIn.add("kotlin.uuid.ExperimentalUuidApi")
    }
}
