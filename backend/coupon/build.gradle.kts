plugins{
	alias(libs.plugins.spring.boot)
}

dependencies {
	implementation(project(":common-web"))
	implementation(project(":common-data"))
	annotationProcessor(project(":common-data"))

	implementation(libs.spring.boot.starter.web)
	implementation(libs.spring.boot.starter.data.jpa)
	implementation(libs.spring.boot.starter.aop)
	implementation(libs.spring.boot.starter.security)

	implementation(
		group = "com.querydsl",
		name = "querydsl-jpa",
		version = libs.versions.querydsl.get(),
		classifier = "jakarta"
	)

	annotationProcessor(
		group = "com.querydsl",
		name = "querydsl-apt",
		version = libs.versions.querydsl.get(),
		classifier = "jakarta"
	)

	annotationProcessor(libs.bundles.querydsl.processor)

	implementation(libs.spring.retry)

	implementation(libs.mysql.connector.j)

	implementation(libs.springdoc.openapi.starter.webmvc.ui)

	testImplementation(libs.bundles.testing)
}

tasks.test {
	useJUnitPlatform()
}