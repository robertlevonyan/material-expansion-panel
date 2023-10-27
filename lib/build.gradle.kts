plugins {
  id("com.android.library")
  kotlin("android")
  id("com.vanniktech.maven.publish")
}

android {
  compileSdk = 34
  namespace = "com.robertlevonyan.views.expandable"
  defaultConfig {
    minSdk = 16
    targetSdk = 34
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    vectorDrawables.useSupportLibrary = true
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
  }
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("androidx.vectordrawable:vectordrawable:1.1.0")
  implementation("androidx.core:core-ktx:1.12.0")
}
