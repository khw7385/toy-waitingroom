plugins {
    alias(libs.plugins.spring.boot)
}

dependencies{
    implementation(project(":common"))
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.mysql.connector.j)

    testImplementation(libs.spring.boot.starter.test)
}