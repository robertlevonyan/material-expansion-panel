plugins {
  id("com.android.application")
}

android {
  compileSdkVersion(30)
  defaultConfig {
    applicationId = "com.robertlevonyan.views.expandablesample"
    minSdkVersion(16)
    targetSdkVersion(30)
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
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}

dependencies {
  implementation("androidx.appcompat:appcompat:1.2.0")
  implementation("androidx.recyclerview:recyclerview:1.2.0")
  implementation("androidx.constraintlayout:constraintlayout:2.0.4")
  implementation("androidx.cardview:cardview:1.0.0")
  implementation("com.robertlevonyan.view:MaterialExpansionPanel:2.1.0")
}
