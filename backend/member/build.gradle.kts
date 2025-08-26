plugins {
    alias(libs.plugins.spring.boot)
}

dependencies{
    implementation(project(":common-web"))
    implementation(project(":common-data"))
    implementation(project(":common-jwt"))

    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.boot.starter.actuator)

    implementation(libs.spring.cloud.starter.circuitbraker.resilience4j)

    implementation(libs.springdoc.openapi.starter.webmvc.ui)

    implementation(libs.mysql.connector.j)

    implementation(libs.bundles.jwt)

    testImplementation(libs.bundles.testing)
}