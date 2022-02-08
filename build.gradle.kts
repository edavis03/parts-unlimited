import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.springframework.boot.gradle.plugin.ResolveMainClassName

group = "mil.army.futures"
version = "0.0.1-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

plugins {
    val kotlinVersion = "1.6.10"
    val springBootVersion = "2.6.2"
    val springDependencyManagementVersion = "1.0.11.RELEASE"

    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version springDependencyManagementVersion
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
}

dependencies {
    implementation(kotlin("reflect"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.flywaydb:flyway-core")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")


    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
    }
    testImplementation("io.mockk:mockk:1.12.2")
    testImplementation("com.ninja-squad:springmockk:3.1.0")
}

tasks {

    withType<Test> {
        useJUnitPlatform()

        testLogging {
            exceptionFormat = TestExceptionFormat.FULL
            events(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
        }
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    val installFrontend by register<Exec>("installFrontend") {
        inputs.file(file("$projectDir/frontend/yarn.lock"))
        inputs.file(file("$projectDir/frontend/package.json"))
        outputs.dir(file("$projectDir/frontend/node_modules"))
        commandLine("yarn", "--cwd", "frontend", "install")
    }

    val testFrontend by register<Exec>("testFrontend") {
        dependsOn(installFrontend)
        commandLine("yarn", "--cwd", "frontend", "test", "--watchAll=false")
    }

    val buildFrontend by register<Exec>("buildFrontend") {
        dependsOn(installFrontend)
        mustRunAfter(testFrontend)
        inputs.dir(file("$projectDir/frontend/src"))
        inputs.dir(file("$projectDir/frontend/public"))
        outputs.dir("$projectDir/frontend/build")
        commandLine("yarn", "--cwd", "frontend", "build")
    }

    val copyFrontend by register<Sync>("copyFrontend") {
        dependsOn(buildFrontend)
        from("$projectDir/frontend/build")
        into(file("$buildDir/resources/main/static"))
        doLast {
            println("copied built frontend to static resources")
        }
    }

    val cleanFrontend by register<Delete>("cleanFrontend") {
        delete(file("./frontend/build"))
        delete(file("./src/main/resources/static"))
    }

    withType<ResolveMainClassName> {
        dependsOn(copyFrontend)
    }

    clean {
        dependsOn(cleanFrontend)
    }

    getByName<Jar>("jar") {
        enabled = false
    }
}