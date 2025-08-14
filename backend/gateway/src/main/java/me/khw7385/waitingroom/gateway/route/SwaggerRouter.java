package me.khw7385.waitingroom.gateway.route;

import lombok.RequiredArgsConstructor;
import me.khw7385.waitingroom.gateway.filter.SwaggerRouteFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.*;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.*;

@Configuration
@RequiredArgsConstructor
public class SwaggerRouter {
    private final SwaggerRouteFilter swaggerRouteFilter;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return route()
                .route(path("/{serviceName}/api-docs"), http())
                .filter(this.swaggerRouteFilter)
                .build();
    }
}
