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

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web:2.2.6.RELEASE'
    implementation 'com.fasterxml.jackson.module:jackson-module-kotlin:2.10.3'
    implementation 'org.springframework.boot:spring-boot-starter:2.2.6.RELEASE'
    implementation 'org.jetbrains.kotlin:kotlin-reflect:1.3.71'
    implementation 'org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.71'
    implementation 'org.springframework.kafka:spring-kafka:2.3.7.RELEASE'
    implementation 'org.slf4j:slf4j-api:1.7.30'
    implementation 'ch.qos.logback.contrib:logback-json-classic:0.1.5'
    implementation 'ch.qos.logback.contrib:logback-jackson:0.1.5'
    implementation 'org.springframework.boot:spring-boot-starter-webflux:2.2.6.RELEASE'
    testImplementation 'org.springframework.boot:spring-boot-starter-test:2.2.6.RELEASE'
    testImplementation 'org.springframework.kafka:spring-kafka-test:2.3.7.RELEASE'
    testImplementation 'org.awaitility:awaitility:3.0.0'
}

group = 'com.epam'
version = '0.0.1-SNAPSHOT'
description = 'shopping'
sourceCompatibility = '1.8'

tasks.withType<Test> {
    useJUnitPlatform()
}
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
