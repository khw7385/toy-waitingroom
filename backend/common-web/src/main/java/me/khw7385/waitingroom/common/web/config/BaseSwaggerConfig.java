package me.khw7385.waitingroom.common.web.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class BaseSwaggerConfig {
    @Value("${gateway.url}")
    private String gatewayUrl;

    protected OpenAPI buildBaseOpenAPI(){
        String securitySchemeName = "bearerAuth";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(securitySchemeName);
        Components components = new Components().addSecuritySchemes(securitySchemeName, new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT"));

        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(components)
                .servers(List.of(new Server().url("http://localhost:8080")));
    }
}
