plugins {
    val kotlinVersion = "1.7.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
}

group = "yyd.mrycat.math"
version = "0.1.0-beta.2"

repositories() {
    mavenCentral()
}
dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")
}
