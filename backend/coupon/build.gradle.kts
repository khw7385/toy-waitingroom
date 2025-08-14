plugins{
	alias(libs.plugins.spring.boot)
}

dependencies {
	implementation(project(":common-web"))
	implementation(project(":common-data"))
	implementation(libs.spring.boot.starter.web)
	implementation(libs.spring.boot.starter.data.jpa)

	testImplementation(libs.bundles.testing)
}