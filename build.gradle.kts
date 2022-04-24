plugins {
    kotlin("multiplatform") version "1.6.21"
    `maven-publish`
    id("org.jetbrains.kotlinx.kover") version "0.5.0"
    id("org.jetbrains.dokka") version "1.6.20"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
}

group = "me.sebj"
version = "0.1.0"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    js(BOTH) {
        nodejs()
        browser()
    }

    linuxX64()
    mingwX64()

    macosX64()
    macosArm64()
    iosX64()
    iosArm64()
    iosArm32()
    iosSimulatorArm64()
    watchosArm32()
    watchosArm64()
    watchosX86()
    watchosX64()
    watchosSimulatorArm64()
    tvosArm64()
    tvosX64()
    tvosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.2")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val jvmMain by getting {
            dependsOn(commonMain)
        }
        val jvmTest by getting {
            dependsOn(commonTest)
        }

        val jsMain by getting {
            dependsOn(commonMain)
        }
        val jsTest by getting {
            dependsOn(commonTest)
        }

        val appleMain = sourceSets.create("appleMain") {
            dependsOn(commonMain)
        }
        val appleTest = sourceSets.create("appleTest") {
            dependsOn(commonTest)
        }

        listOf(
            macosX64(),
            macosArm64(),
            iosX64(),
            iosArm64(),
            iosArm32(),
            iosSimulatorArm64(),
            watchosArm32(),
            watchosArm64(),
            watchosX86(),
            watchosX64(),
            watchosSimulatorArm64(),
            tvosArm64(),
            tvosX64(),
            tvosSimulatorArm64()
        ).forEach {
            it.apply {
                compilations.getByName("main").source(appleMain)
                compilations.getByName("test").source(appleTest)
            }
        }
    }
}

tasks.dokkaHtml.configure {
    pluginsMapConfiguration.set(
        mapOf(
            "org.jetbrains.dokka.base.DokkaBase" to "{ \"footerMessage\": \"© Copyright Sebastian Jachec\" }",
            "org.jetbrains.dokka.versioning.VersioningPlugin" to "{ \"version\": \"main\" }"
        )
    )
    dependencies {
        dokkaHtmlPlugin("org.jetbrains.dokka:versioning-plugin:1.6.20")
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "me.sebj"
            artifactId = "time"
            version = "0.1.0"

            from(components["java"])
        }
    }
}
