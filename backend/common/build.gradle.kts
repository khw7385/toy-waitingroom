dependencies{
	implementation(libs.spring.boot.starter.data.jpa)
}

tasks.named<org.gradle.api.tasks.bundling.Jar>("jar") {
	enabled = true
}