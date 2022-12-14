
plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id(Libraries.Jacoco.jacocoAndroid)
}

jacoco {
    toolVersion = Libraries.Jacoco.version
}

android {
    defaultConfig {
        compileSdk = AndroidSdk.targetSdkVersion
        minSdk = AndroidSdk.minSdkVersion
        targetSdk = AndroidSdk.targetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Libraries.Modules.common))
    implementation(project(Libraries.Modules.domain))
    implementation(project(Libraries.Modules.network))
    implementation(project(Libraries.Modules.local))

    //Ktx Core
    implementation(Libraries.AndroidX.coreKtx)

    implementation(Libraries.Kotlin.stdlib)
    //Coroutine
    implementation(Libraries.Coroutines.core)

    //Timber
    implementation(Libraries.Timber.timber)

    //Hilt
    implementation(Libraries.Google.Hilt.android)
    kapt(Libraries.Google.Hilt.compiler)

    //Tests

    testImplementation(Libraries.JUnit.junit)
    testImplementation(Libraries.Mockk.mockk)
    testImplementation(Libraries.Google.truth)

    androidTestImplementation(Libraries.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libraries.AndroidX.Test.Ext.junitKTX)
    androidTestImplementation(Libraries.AndroidX.Test.espressoCore)
}