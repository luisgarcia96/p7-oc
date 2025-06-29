plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace  = "com.openclassrooms.arista"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.openclassrooms.arista"
        minSdk        = 24
        targetSdk     = 34
        versionCode   = 1
        versionName   = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions { jvmTarget = "17" }

    buildFeatures { viewBinding = true }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:2.0.21"))

    /* Room */
    val room = "2.7.2"
    implementation("androidx.room:room-runtime:$room")
    implementation("androidx.room:room-ktx:$room")
    implementation("androidx.room:room-paging:$room")
    ksp("androidx.room:room-compiler:$room")
    testImplementation("androidx.room:room-testing:$room")

    /* Hilt */
    val hilt = "2.56.2"
    implementation("com.google.dagger:hilt-android:$hilt")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
    ksp("com.google.dagger:hilt-compiler:$hilt")

    /* AndroidX & Material */
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity-ktx:1.8.0")
    implementation("androidx.fragment:fragment-ktx:1.6.1")

    /* Desugaring */
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

    /* Tests */
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.4.0")
    testImplementation("androidx.arch.core:core-common:2.2.0")
}
