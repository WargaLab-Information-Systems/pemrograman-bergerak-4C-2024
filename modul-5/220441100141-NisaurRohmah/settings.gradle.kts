pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        //mengimpor dependensi dari jitpack
        // untuk menambahkan library atau modul yang mungkin tidak tersedia di repositori Maven
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "My App"
include(":app")
 