plugins {
  id("com.android.application")
}

android {
  compileSdk = 32
  defaultConfig {
    applicationId = "com.robertlevonyan.views.expandablesample"
    minSdk = 16
    targetSdk = 32
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {
  implementation("androidx.appcompat:appcompat:1.4.1")
  implementation("androidx.recyclerview:recyclerview:1.2.1")
  implementation("androidx.constraintlayout:constraintlayout:2.1.3")
  implementation("androidx.cardview:cardview:1.0.0")
  implementation("com.robertlevonyan.view:MaterialExpansionPanel:2.1.4")
}
