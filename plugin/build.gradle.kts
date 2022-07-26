plugins {
    id("project.common-conventions")
    alias(libs.plugins.shadow)
}

repositories {
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    api(project(":api"))
    compileOnly(libs.spigot)
    compileOnly(libs.placeholder)
    compileOnly("net.kyori:adventure-text-minimessage:4.11.0")
}

tasks {
    shadowJar {
        archiveBaseName.set("SternalBoard")
        destinationDirectory.set(file("$rootDir/bin/"))
        minimize()
    }

    clean {
        delete("${rootDir}/bin/")
    }
}