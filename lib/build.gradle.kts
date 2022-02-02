plugins {
  id("com.android.library")
  kotlin("android")
  id("com.vanniktech.maven.publish")
}

android {
  compileSdk = 32
  defaultConfig {
    minSdk = 16
    targetSdk = 32
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    vectorDrawables.useSupportLibrary = true
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
}

allprojects {
  plugins.withId("com.vanniktech.maven.publish") {
    mavenPublish {
      sonatypeHost = com.vanniktech.maven.publish.SonatypeHost.S01
    }
  }
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation("androidx.appcompat:appcompat:1.4.1")
  implementation("androidx.vectordrawable:vectordrawable:1.1.0")
  implementation("androidx.core:core-ktx:1.7.0")
}
