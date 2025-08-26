dependencies{
	implementation(libs.spring.boot.starter.web)
	implementation(libs.spring.boot.starter.security)

	implementation(libs.springdoc.openapi.starter.webmvc.ui)
}

tasks.named<org.gradle.api.tasks.bundling.Jar>("jar") {
	enabled = true
}