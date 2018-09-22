import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins{
    kotlin("jvm") version "1.2.10"
    id("java")
    id("org.jetbrains.kotlin.plugin.allopen") version "1.2.10"
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
}
repositories {
    mavenCentral()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

allOpen{
    annotation("buildAnnotations.Open")
}

dependencies{
    compile("com.mashape.unirest:unirest-java:1.4.9")
}