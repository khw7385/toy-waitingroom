package me.khw7385.waitingroom.gateway.filter;

import me.khw7385.waitingroom.gateway.properties.ServiceProperties;
import org.springframework.cloud.gateway.server.mvc.common.MvcUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;
import java.util.Map;

import static org.springframework.cloud.gateway.server.mvc.common.MvcUtils.GATEWAY_REQUEST_URL_ATTR;

@Component
public class SwaggerRouteFilter implements HandlerFilterFunction<ServerResponse, ServerResponse> {
    private final Map<String, String> uriMap;

    public SwaggerRouteFilter(ServiceProperties serviceProperties) {
        uriMap = Map.of(
                "member-service", serviceProperties.member(),
                "coupon-service", serviceProperties.coupon()
        );
    }

    @Override
    public ServerResponse filter(ServerRequest request, HandlerFunction<ServerResponse> next) throws Exception {
        String serviceName = request.pathVariable("serviceName");

        URI newUri = URI.create(rewriteUrl(serviceName));
        MvcUtils.setRequestUrl(request, newUri);

        return next.handle(request);
    }

    private String rewriteUrl(String serviceName) {
        return uriMap.get(serviceName);
    }
}
