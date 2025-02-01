plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "ma.tp_celebrity_list"
    compileSdk = 34

    defaultConfig {
        applicationId = "ma.tp_celebrity_list"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}


dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.0")


    //recycleview
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    //circle image
    implementation("com.github.bumptech.glide:glide:4.15.0")
    //glide
    implementation("de.hdodenhof:circleimageview:3.0.1")



}

