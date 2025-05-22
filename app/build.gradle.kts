plugins {
    kotlin("jvm") version "2.1.21"
    kotlin("plugin.serialization") version "2.1.20" // ✅ Required!
    id("com.google.devtools.ksp") version "2.1.21-2.0.1"
    application
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

val log4jVersion = "2.20.0"
dependencies {
    // Logger
    implementation("org.apache.logging.log4j:log4j-api:${log4jVersion}")
    implementation("org.apache.logging.log4j:log4j-core:${log4jVersion}")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:${log4jVersion}")
    // Ktor
    implementation("io.ktor:ktor-server-core:2.3.9")
    implementation("io.ktor:ktor-server-netty:2.3.9")

    implementation("io.ktor:ktor-server-content-negotiation:2.3.9")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.9")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3") // or latest

    implementation("io.ktor:ktor-client-core:2.3.9")
    implementation("io.ktor:ktor-client-cio:2.3.9")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.9")

    // Koin for DI
    implementation("io.insert-koin:koin-ktor:3.4.3")

    // Vert.x Core + Restate
    implementation("io.vertx:vertx-core:4.5.3")
    implementation("dev.restate:sdk-core:2.1.0")
    implementation("dev.restate:sdk-api:2.1.0")
    implementation("dev.restate:client:2.1.0")
    implementation("dev.restate:client-kotlin:2.1.0")
    implementation("dev.restate:sdk-api-kotlin:2.1.0")
    implementation("dev.restate:sdk-http-vertx:2.1.0")
    ksp("dev.restate:sdk-api-kotlin-gen:2.1.0")
    annotationProcessor("dev.restate:sdk-api-gen:2.1.0")

    // Use the Kotlin JUnit 5 integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation(libs.junit.jupiter.engine)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation("io.kotest:kotest-framework-engine:5.8.0")
}

configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "org.jetbrains.kotlinx" && requested.name.startsWith("kotlinx-coroutines")) {
            useVersion("1.7.3")
            because("Ensure all coroutines modules use the same version to avoid NoSuchMethodError")
        }
        if (requested.group == "org.apache.logging.log4j") {
            useVersion(log4jVersion)
            because("All log4j modules must match")
        }
    }
}

// Apply a specific Java toolchain to ease working on different environments.
kotlin {
    jvmToolchain(21)
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}

application {
    // Define the main class for the application.
    mainClass = "org.shakirfattani.AppKt"
}

tasks.test {
    useJUnitPlatform()
}