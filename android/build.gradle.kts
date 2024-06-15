import publish.createFromSpec
import publish.getProjectInfo

plugins {
  alias(libs.plugins.com.android.library)
}

/* -----------------------------------------------------
 * Project configuration
 * ----------------------------------------------------- */

val projectSpec = getProjectInfo()
group = projectSpec.group
version = projectSpec.version

/* -----------------------------------------------------
 * Android configuration
 * ----------------------------------------------------- */

android {
  namespace = projectSpec.group
  compileSdk = 34
  
  defaultConfig {
    minSdk = 24
    
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
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
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  
  publishing {
    singleVariant("release") {
      withJavadocJar()
      withSourcesJar()
    }
  }
}

/* -----------------------------------------------------
 * Maven publications
 * ----------------------------------------------------- */

afterEvaluate {
  publishing.publications {
    // Generate maven publications
    createFromSpec(projectSpec, project)
  }
}

/* -----------------------------------------------------
 * Project dependencies
 * ----------------------------------------------------- */

dependencies {
  implementation(libs.android.com.android.annotation)
  implementation(libs.android.com.android.compat)
  implementation(libs.android.com.android.material)
  implementation(libs.android.com.github.uoxx3.android.core.utilities)
  
  // Java resting
  testImplementation(platform(libs.java.org.junit.jupiter.bom))
  testImplementation(libs.java.org.junit.jupiter.jupiter)
  
  // Android testing
  androidTestImplementation(libs.android.com.android.test.junit)
  androidTestImplementation(libs.android.com.android.test.espresso)
}