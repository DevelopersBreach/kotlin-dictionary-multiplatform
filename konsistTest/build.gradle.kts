plugins {
    kotlin("jvm")
    id("java")
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    testImplementation("com.lemonappdev:konsist:0.17.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.11.3")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
