plugins {
    val kotlinVersion = "1.7.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    id("org.jetbrains.dokka") version "1.5.30"//javadoc生成插件
}

group = "yyd.mrycat.math"
version = "0.1.0-beta.2"
/*
tasks.dokkaJavadoc.configure {
    // 导出的文档目录路径
    //outputDirectory.set(new File(rootDir, "javadoc"))
    dokkaSourceSets {
        named("main") {
            noAndroidSdkLink.set(true)
            noStdlibLink.set(true)
            noJdkLink.set(true)
        }
        configureEach {
            // 包的定义
            includes.from("yyd.mrycat.math")
        }
    }
}*/
repositories {
    mavenCentral()
}
dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")
}