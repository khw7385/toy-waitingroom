plugins {
    alias(libs.plugins.spring.boot)
}

dependencies {
    implementation(project(":common-web"))
    implementation(project(":common-jwt"))
    implementation(libs.spring.cloud.starter.gateway.server.mvc)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.boot.starter.actuator)

    implementation(libs.springdoc.openapi.starter.webmvc.ui)
}