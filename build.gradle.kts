plugins {
    java
}

group = "org.example"
version = "1.0.0.Final"
description = "Hibernate 6.6 Test"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
//    implementation(platform("org.hibernate.orm:hibernate-platform:6.5.3.Final"))
    implementation(platform("org.hibernate.orm:hibernate-platform:6.6.4.Final"))
    implementation("org.hibernate.orm:hibernate-core")
    implementation("com.h2database:h2:2.3.232")
    implementation("jakarta.validation:jakarta.validation-api:3.1.0")

    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")

    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.hibernate.orm:hibernate-testing")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-launcher")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.assertj:assertj-core:3.27.1")
}
