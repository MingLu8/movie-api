import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
	kotlin("plugin.jpa") version "1.6.10"
	kotlin("plugin.allopen") version "1.4.32"
	kotlin("kapt") version "1.4.32"
}

group = "movie"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.security.oauth:spring-security-oauth2:2.5.1.RELEASE")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
//	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
	implementation("io.github.microutils:kotlin-logging-jvm:2.1.21")
	implementation("com.google.code.gson:gson:2.9.0")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springdoc:springdoc-openapi-data-rest:1.6.7")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.7")
	implementation("org.springdoc:springdoc-openapi-kotlin:1.6.7")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.auth0:java-jwt:3.19.1")

	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")
	runtimeOnly("org.springframework.boot:spring-boot-devtools")

	kapt("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.amshove.kluent:kluent:1.68")
	testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.18")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		exclude(module = "mockito-core")
//		exclude(module = "junit")
	}

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("com.ninja-squad:springmockk:3.1.1")
	testImplementation("org.junit.jupiter:junit-jupiter-api")
	testImplementation("com.ninja-squad:springmockk:3.0.1")
	testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:2.0.18")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.Embeddable")
	annotation("javax.persistence.MappedSuperclass")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform{
//		includeEngines("spek2", "junit")
	}
}
