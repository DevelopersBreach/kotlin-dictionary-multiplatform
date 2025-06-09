plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.pinterest.ktlint:ktlint-cli-ruleset-core:1.3.0")
    implementation("com.pinterest.ktlint:ktlint-cli:1.3.0")
    implementation("com.pinterest.ktlint:ktlint-rule-engine-core:1.3.0")
    implementation("com.pinterest.ktlint:ktlint-test:1.3.0")
}