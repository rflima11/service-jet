package br.com.businesstec.servicejet.client;

import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.http.JetAuth;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FeignConfig {

    private final JetProperties jetProperties;

    private static final String AUTHORIZATION_HEADER = "Authorization";

    public FeignConfig(JetProperties jetProperties) {
        this.jetProperties = jetProperties;

    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.query("integrationKey", jetProperties.getIntegrationKeyBase64());
        };

    }

}
