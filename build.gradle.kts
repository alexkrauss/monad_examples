plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.20")
    scala
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    // Kotlin code
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.assertj:assertj-core:3.11.1")

    // Scala code
    implementation("org.scala-lang:scala-library:2.13.1")
}
