package me.khw7385.waitingroom.member.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient couponBaseRestClient(@Value("${service.host.coupon}") String host){
        return RestClient.builder()
                .baseUrl(host)
                .build();
    }
}
