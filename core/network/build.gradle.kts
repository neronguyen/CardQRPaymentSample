plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.core.model)
}

kotlin {
    jvmToolchain(25)
}
