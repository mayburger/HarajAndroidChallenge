plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "com.example.harajtask"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        vectorDrawables.useSupportLibrary = true

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

    flavorDimensions("default")

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures{
        dataBinding = true
    }
}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")

    implementation(kotlinDependencies.kotlin)
    implementation(kotlinDependencies.coroutinesCore)
    implementation(kotlinDependencies.coroutinesAndroid)

    implementation(jetpackDependencies.coreKtx)
    implementation(jetpackDependencies.appCompat)
    implementation(jetpackDependencies.constraintLayout)
    implementation(jetpackDependencies.legacySupport)
    implementation(jetpackDependencies.animatedVectorDrawable)
    implementation(jetpackDependencies.cardView)
    implementation(jetpackDependencies.lifecycleExtensions)
    implementation(jetpackDependencies.lifecycleCompiler)
    implementation(jetpackDependencies.lifecycleViewModel)
    implementation(jetpackDependencies.lifecycleLivedata)
    implementation(jetpackDependencies.lifecycleCoroutines)

    implementation(hiltDependencies.hilt)
    implementation(hiltDependencies.hiltViewModel)

    implementation(retrofitDependencies.gson)
    implementation(retrofitDependencies.gsonConverter)

    implementation(googleDependencies.material)
    implementation(googleDependencies.flexbox)

    implementation(roomDependencies.room)
    implementation(roomDependencies.ktx)

    implementation(pagingDependencies.paging)
    implementation(glideDependencies.glide)

    implementation("androidx.activity:activity-ktx:1.2.4")
    implementation("android.arch.lifecycle:extensions:1.1.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")

    implementation("org.ocpsoft.prettytime:prettytime:4.0.1.Final")

    kapt(glideDependencies.glideCompiler)
    kapt(roomDependencies.compiler)
    kapt(hiltDependencies.hiltCompiler)
    kapt(hiltDependencies.hiltAndroidXCompiler)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

}

