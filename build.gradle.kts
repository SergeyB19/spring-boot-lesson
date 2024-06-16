import com.ewerk.gradle.plugins.tasks.QuerydslCompile
import org.gradle.kotlin.dsl.support.kotlinCompilerOptions

plugins {
    id("java")
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.5"
    id("com.ewerk.gradle.plugins.querydsl") version "1.0.10"
}

sourceSets{
    main{
        java{
            srcDirs ("$buildDir/generated-source")
        }
    }
}

group = "com.dmdev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.postgresql:postgresql")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    // https://mvnrepository.com/artifact/com.querydsl/querydsl-apt
    implementation("com.querydsl:querydsl-apt:5.1.0")
// https://mvnrepository.com/artifact/com.querydsl/querydsl-jpa
    implementation("com.querydsl:querydsl-jpa:5.1.0")

    compileOnly("org.projectlombok:lombok:1.18.30")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    annotationProcessor("org.projectlombok:lombok:1.18.30")
    annotationProcessor("com.querydsl:querydsl-apt")




}

querydsl{
    jpa = true
    querydslSourcesDir = "$buildDir/generated-source"

}








tasks.test {
    useJUnitPlatform()

}


