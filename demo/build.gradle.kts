plugins {
  id("com.android.application")
}

android {
  compileSdk = 34
  namespace = "com.robertlevonyan.views.expandablesample"
  defaultConfig {
    applicationId = "com.robertlevonyan.views.expandablesample"
    minSdk = 16
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables.useSupportLibrary = true
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
}

dependencies {
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("androidx.recyclerview:recyclerview:1.3.2")
  implementation("androidx.constraintlayout:constraintlayout:2.1.4")
  implementation("androidx.cardview:cardview:1.0.0")
  implementation("com.robertlevonyan.view:MaterialExpansionPanel:2.1.6")
}
