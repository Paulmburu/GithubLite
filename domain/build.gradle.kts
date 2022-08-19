plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {

    implementation(project(Libraries.Modules.common))

    implementation(Libraries.Kotlin.stdlib)
    implementation(Libraries.Coroutines.core)

    //Test
    implementation(Libraries.JUnit.junit)
    implementation(Libraries.Google.truth)
    implementation(project(mapOf("path" to ":common")))
    implementation(project(mapOf("path" to ":common")))
}