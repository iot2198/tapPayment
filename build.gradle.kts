plugins {
    id("java")
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("org.springframework.boot:spring-boot-starter-web:3.1.4")
    implementation("org.springframework.boot:spring-boot:3.1.4")
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("org.liquibase:liquibase-core:4.23.2")
    implementation("net.lbruun.springboot:preliquibase-spring-boot-starter:1.4.0")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:3.1.4")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.1.4")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")
    implementation("org.springframework.boot:spring-boot-starter-data-rest:3.1.4")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.1.4")
    implementation("org.springframework.boot:spring-boot-starter:3.1.4")
    implementation("org.springframework.boot:spring-boot-devtools:3.1.4")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("org.springframework.data:spring-data-commons:3.1.4")

    testImplementation("org.testcontainers:testcontainers:1.19.0")
    testImplementation("org.testcontainers:junit-jupiter:1.19.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("io.grpc:grpc-testing:1.58.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.4")
    testImplementation("org.testcontainers:postgresql:1.19.0")

}
