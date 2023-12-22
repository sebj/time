plugins {
    kotlin("multiplatform") version "1.9.22"
    `maven-publish`
    id("signing")
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.13.2"
    id("org.jetbrains.kotlinx.kover") version "0.7.5"
    id("org.jetbrains.dokka") version "1.9.10"
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
}

group = "me.sebj"
version = "0.4.9"

repositories {
    mavenCentral()
}

kotlin {
    explicitApi()

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_18.toString()
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    linuxX64()
    mingwX64()

    macosX64()
    macosArm64()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    watchosArm32()
    watchosArm64()
    watchosX64()
    watchosSimulatorArm64()
    tvosArm64()
    tvosX64()
    tvosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
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
            iosSimulatorArm64(),
            watchosArm32(),
            watchosArm64(),
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
        dokkaHtmlPlugin("org.jetbrains.dokka:versioning-plugin:1.9.10")
    }
}

val dokkaOutputDir = "$buildDir/dokka"

tasks.getByName<org.jetbrains.dokka.gradle.DokkaTask>("dokkaHtml") {
    outputDirectory.set(file(dokkaOutputDir))
}

val deleteDokkaOutputDir by tasks.register<Delete>("deleteDokkaOutputDirectory") {
    delete(dokkaOutputDir)
}

val javadocJar = tasks.register<Jar>("javadocJar") {
    dependsOn(deleteDokkaOutputDir, tasks.dokkaHtml)
    archiveClassifier.set("javadoc")
    from(dokkaOutputDir)
}

val sonatypeUsername: String? = System.getenv("SONATYPE_USERNAME")
val sonatypePassword: String? = System.getenv("SONATYPE_PASSWORD")

publishing {
    publications {
        repositories {
            maven {
                name = "oss"
                val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl

                credentials {
                    username = sonatypeUsername
                    password = sonatypePassword
                }
            }
        }

        withType<MavenPublication> {
            artifact(javadocJar)

            pom {
                name.set("time")
                description.set("Type-safe time periods for the Kotlinx-datetime multiplatform date/time library")
                url.set("https://github.com/sebj/time")

                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://github.com/sebj/time/blob/main/LICENSE")
                    }
                }
                issueManagement {
                    system.set("GitHub")
                    url.set("https://github.com/sebj/time/issues")
                }
                scm {
                    connection.set("https://github.com/sebj/time.git")
                    url.set("https://github.com/sebj/time")
                }
                developers {
                    developer {
                        name.set("Seb Jachec")
                        email.set("hi@sebj.me")
                    }
                }
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(
        System.getenv("GPG_PRIVATE_KEY"),
        System.getenv("GPG_PRIVATE_PASSWORD")
    )
    sign(publishing.publications)
}

// TODO: remove after https://youtrack.jetbrains.com/issue/KT-46466 is fixed
project.tasks.withType(AbstractPublishToMaven::class.java).configureEach {
    dependsOn(project.tasks.withType(Sign::class.java))
}
