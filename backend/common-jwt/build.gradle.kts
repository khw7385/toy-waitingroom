dependencies {
    implementation(project(":common-web"))
    implementation(libs.spring.boot.autoconfigure)
    implementation(libs.bundles.jwt)

    testImplementation(libs.junit.jupiter)
}

tasks.named<org.gradle.api.tasks.bundling.Jar>("jar") {
    enabled = true
}