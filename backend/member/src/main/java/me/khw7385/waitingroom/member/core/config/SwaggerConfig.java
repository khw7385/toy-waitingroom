package me.khw7385.waitingroom.member.core.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import me.khw7385.waitingroom.common.web.config.BaseSwaggerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Member 서비스 API 명세서",
                description = "이 API 명세서 프로젝트의 백엔드 중 Member 서비스 API를 문서화합니다.",
                version = "v1.0.0"
        )
)
@Configuration
public class SwaggerConfig extends BaseSwaggerConfig {

        @Bean
        public OpenAPI openAPI() {
                return buildBaseOpenAPI();
        }
}
