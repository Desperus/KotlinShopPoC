import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") version "1.3.71"
    kotlin("plugin.spring") version "1.3.71"
}

repositories {
    mavenCentral()
}

object Versions {
    const val LOGBACK_JSON = "0.1.5"
    const val SPRING_KAFKA = "2.3.7.RELEASE"
    const val COROUTINES = "1.3.5"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.3")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.kafka:spring-kafka:${Versions.SPRING_KAFKA}")
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("ch.qos.logback.contrib:logback-json-classic:${Versions.LOGBACK_JSON}")
    implementation("ch.qos.logback.contrib:logback-jackson:${Versions.LOGBACK_JSON}")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${Versions.COROUTINES}")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.springframework.kafka:spring-kafka-test:${Versions.SPRING_KAFKA}")
    testImplementation("org.awaitility:awaitility:3.0.0")
}

group = "com.epam"
version = "0.0.1-SNAPSHOT"
description = "shopping"
java.sourceCompatibility = JavaVersion.VERSION_11

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
