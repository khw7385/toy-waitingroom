package me.khw7385.waitingroom.gateway.route;

import lombok.RequiredArgsConstructor;
import me.khw7385.waitingroom.gateway.filter.JwtAuthenticationFilter;
import me.khw7385.waitingroom.gateway.properties.ServiceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.*;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.*;
import static org.springframework.web.servlet.function.RequestPredicates.*;

@Configuration
@RequiredArgsConstructor
public class ServiceRouter {
    private static final String AUTH_SERVICE = "auth_service";
    private static final String MEMBER_SERVICE = "member_service";
    private static final String COUPON_SERVICE = "coupon_service";

    private final ServiceProperties props;
    private final JwtAuthenticationFilter authenticationFilter;

    @Bean
    public RouterFunction<ServerResponse> authRouter(){
        return route(AUTH_SERVICE)
                .route(path("/auth/**"), http())
                .before(uri(props.member()))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> memberRouter(){
        return route(MEMBER_SERVICE)
                .route(path("/members/**"), http())
                .filter(this.authenticationFilter)
                .before(uri(props.member()))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> couponRouter(){
        return route(COUPON_SERVICE)
                .route(path("/coupons/**"), http())
                .filter(this.authenticationFilter)
                .before(uri(props.coupon()))
                .build();
    }
}
