import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

operator fun String.invoke(): String = rootProject.properties[this] as? String ?: error("Property $this not found")

group = "dev.rdh"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    exclusiveContent {
        forRepository { maven("https://packages.jetbrains.team/maven/p/kpm/public/") }
        filter {
            includeGroup("org.jetbrains.jewel")
        }
    }
    google()
}

dependencies {
    implementation(kotlin("reflect"))
    implementation("com.darkrockstudios:mpfilepicker:3.1.0")
    implementation("com.google.code.findbugs:jsr305:3.0.2")

    implementation("org.jetbrains.jewel:jewel-markdown-int-ui-standalone-styling-241:${"jewel.version"()}")
    implementation("org.jetbrains.jewel:jewel-markdown-extension-gfm-alerts-241:${"jewel.version"()}")
    implementation("org.jetbrains.jewel:jewel-markdown-extension-autolink-241:${"jewel.version"()}")
    implementation("org.jetbrains.jewel:jewel-int-ui-standalone-241:${"jewel.version"()}")
    implementation("org.jetbrains.jewel:jewel-int-ui-decorated-window-241:${"jewel.version"()}")

    implementation(compose.desktop.currentOs) {
        exclude(group = "org.jetbrains.compose.material")
    }
}

tasks.withType<JavaExec> {
    javaLauncher = project.javaToolchains.launcherFor {
        languageVersion = JavaLanguageVersion.of(17)
        vendor = JvmVendorSpec.JETBRAINS
    }
    setExecutable(javaLauncher.map { it.executablePath.asFile.absolutePath }.get())
}

compose.desktop {
    application {
        mainClass = "org.jetbrains.jewel.samples.standalone.MainKt"

        buildTypes.release.proguard {
            configurationFiles.from(project.file("compose-desktop.pro"))
        }

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi)
            packageName = "Orbital2"
            packageVersion = project.version.toString()
            licenseFile = rootProject.file("LICENSE")

            macOS {
                dockName = "Orbital2"
                bundleID = "dev.rdh.orbital2"
                iconFile = file("icons/icon-mac.icns")
            }

            windows {
                iconFile = file("icons/icon-win.ico")
            }
        }
    }
}
