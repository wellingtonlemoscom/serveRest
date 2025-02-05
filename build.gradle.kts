

plugins {
    kotlin("jvm") version "1.9.22"
    application
}

group = "serve.rest"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation ("io.rest-assured:kotlin-extensions:5.5.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")

}

tasks.test {
    useJUnitPlatform()
}

tasks.create("smokeTest", Test::class){
    useJUnitPlatform() {
        includeTags("smoke")
    }
    testLogging {
        events(org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED, org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED, org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED)
    }
}

tasks.create("regressionTest", Test::class){
    useJUnitPlatform() {
        includeTags("regression")
    }
    testLogging {
        events(org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED, org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED, org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED)
    }
}

application {
    mainClass.set("MainKt")
}

