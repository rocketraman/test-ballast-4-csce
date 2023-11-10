@file:Suppress("UnusedPrivateMember")

plugins {
  kotlin("multiplatform")
  id("org.jetbrains.compose") version "1.5.10"
}

group = "com.rocketraman"
version = "1.0-SNAPSHOT"

kotlin {
  js(IR) {
    binaries.executable()
    browser()
  }
  sourceSets {
    all {
      languageSettings {
        languageVersion = "1.9"
        progressiveMode = true
      }
    }
    val commonMain by getting {
      dependencies {
        api(compose.runtime)

        api("co.touchlab:kermit:1.0.0")

        api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
        api("org.jetbrains.kotlinx:kotlinx-coroutines-debug:1.7.3")

        api("org.kodein.di:kodein-di:7.18.0")

        api("io.github.copper-leaf:ballast-core:4.0.0")
        api("io.github.copper-leaf:ballast-navigation:4.0.0")
        api("io.github.copper-leaf:ballast-repository:4.0.0")

        implementation("io.github.copper-leaf:ballast-debugger-client:4.0.0")
      }
    }

    val commonTest by getting {
      dependencies {
        implementation(kotlin("test"))
      }
    }

    val jsMain by getting {
      dependencies {
        api(compose.html.core)
      }
    }
  }
}
