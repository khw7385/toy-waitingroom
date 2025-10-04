plugins {
	alias(libs.plugins.spring.boot)
}

dependencies{
	implementation(libs.spring.boot.starter.web)
	implementation(libs.spring.boot.starter.data.redis)

	implementation(libs.springdoc.openapi.starter.webmvc.ui)
}