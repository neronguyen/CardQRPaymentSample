@file:Suppress("UnstableApiUsage")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "CardQRPaymentSample"

include(":core:model")
include(":core:data")
include(":core:database")
include(":core:sdk")
include(":core:network")
