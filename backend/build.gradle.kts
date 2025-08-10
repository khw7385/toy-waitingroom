plugins{
    `kotlin-dsl`
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.spring.dependency.management) apply false
    alias(libs.plugins.kotlin.jvm) apply false
}

// 모든 프로젝트에 적용되는 설정
allprojects {
    // 모든 모듈이 의존성 내려받을 중앙 저장소 지정한다.
    repositories{
        mavenCentral()
    }
}

// 하위 프로젝트에 적용되는 설정
subprojects {
    group = "me.khw7385.waitingroom"
    version = "0.0.1-SNAPSHOT"

    apply(plugin = "java-library")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "io.spring.dependency-management")

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>{
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }

    tasks.withType<Test>{
        useJUnitPlatform()
    }
}