package br.com.businesstec.servicejet.client.config;

import br.com.businesstec.servicejet.client.AuthClienteJet;
import br.com.businesstec.servicejet.config.JetProperties;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
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
            requestTemplate.query("integrateCategory", "true");
        };

    }

}
