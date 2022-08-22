plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {


    defaultConfig {
        applicationId = "github.paulmburu.githublite"
        compileSdk = 32
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Libraries.Modules.common))
    implementation(project(Libraries.Modules.local))
    implementation(project(Libraries.Modules.network))
    implementation(project(Libraries.Modules.repository))
    implementation(project(Libraries.Modules.domain))

    //Ktx Core
    implementation(Libraries.AndroidX.coreKtx)

    //UI
    implementation(Libraries.AndroidX.appcompat)
    implementation(Libraries.Google.material)
    implementation(Libraries.AndroidX.Constraint.constraintLayout)
    implementation(Libraries.AndroidX.Fragment.navigationFragment)
    implementation(Libraries.AndroidX.Fragment.navigationUi)

    //Hilt
    implementation(Libraries.Google.Hilt.android)
    kapt(Libraries.Google.Hilt.compiler)

    // Glide
    implementation(Libraries.Glide.glide)
    annotationProcessor(Libraries.Glide.annotationProcessor)

    //Timber
    implementation(Libraries.Timber.timber)

    //Test
    testImplementation(Libraries.JUnit.junit)
    testImplementation(Libraries.Mockk.mockk)
    testImplementation(Libraries.Google.truth)
    testImplementation(Libraries.AndroidX.Test.archCore)
    testImplementation(Libraries.Coroutines.test)

    androidTestImplementation(Libraries.AndroidX.Test.runner)
    androidTestImplementation(Libraries.AndroidX.Test.rules)
    androidTestImplementation(Libraries.AndroidX.Test.Ext.junitKTX)
    androidTestImplementation(Libraries.AndroidX.Test.espressoCore)
    androidTestImplementation(Libraries.Kakao.kakao)
    androidTestImplementation (Libraries.Hamcrest.hamcrest)
}