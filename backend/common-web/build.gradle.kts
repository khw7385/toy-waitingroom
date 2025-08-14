dependencies{
	implementation(libs.spring.boot.starter.web)
}

tasks.named<org.gradle.api.tasks.bundling.Jar>("jar") {
	enabled = true
}