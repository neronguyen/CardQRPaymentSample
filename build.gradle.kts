plugins {
    alias(libs.plugins.kotlin.jvm) apply false
}

group = "io.github.neronguyenvn.paymentsample"
version = "0.0.1-SNAPSHOT"

subprojects {
    group = rootProject.group
    version = rootProject.version
}
