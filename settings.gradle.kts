pluginManagement {
  val kotlinVersion: String by System.getProperties()
  plugins {
    resolutionStrategy {
      eachPlugin {
        if (requested.id.id.startsWith("org.jetbrains.kotlin.")) useVersion(kotlinVersion)
      }
    }
  }
}

dependencyResolutionManagement {
  repositories {
    mavenCentral()
  }
}

rootProject.name = "test-ballast-4-csce"
