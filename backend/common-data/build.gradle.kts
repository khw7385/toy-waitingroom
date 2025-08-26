dependencies {
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.p6spy.spring.boot.starter)

    implementation(libs.spring.boot.starter.data.jpa)

    implementation("com.querydsl:querydsl-jpa:${libs.versions.querydsl.get()}:jakarta")
    annotationProcessor("com.querydsl:querydsl-apt:${libs.versions.querydsl.get()}:jakarta")

    annotationProcessor(libs.bundles.querydsl.processor)
}
