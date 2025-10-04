# 1. 빌드 단계
FROM amazoncorretto:21 as builder

WORKDIR /app

## Gradle 기본 파일들
COPY gradlew .
COPY gradle gradle/
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradle.properties* .

RUN chmod +x gradlew

# 의존성 다운드드를 위한 build.gradle.kts 파일 (캐시 최적화용)
COPY common-web/build.gradle.kts common-web/
COPY common-jwt/build.gradle.kts common-jwt/
COPY gateway/build.gradle.kts gateway/

RUN ./gradlew dependencies --no-daemon

COPY common-web common-web/
COPY common-jwt common-jwt/
COPY gateway gateway/

RUN ./gradlew clean gateway:build -x test --no-daemon

# 2. 실행 단계
FROM eclipse-temurin:21-jdk-alpine-3.22 as runtime

WORKDIR /app

COPY --from=builder /app/gateway/build/libs/*.jar app.jar

EXPOSE 8082

## 컨테이너 시작 시 메인 명령어
ENTRYPOINT [ "java", "-jar", "app.jar"]

##
CMD ["--spring.profiles.active=dev"]