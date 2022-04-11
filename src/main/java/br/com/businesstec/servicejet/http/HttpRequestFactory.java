package br.com.businesstec.servicejet.http;

import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.enums.ApiPathEnum;
import br.com.businesstec.servicejet.enums.HostEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

@Component
public class HttpRequestFactory {

    private final JetProperties jetProperties;
    private final JetAuth jetAuth;

    public HttpRequestFactory(JetProperties jetProperties, JetAuth jetAuth) {
        this.jetProperties = jetProperties;
        this.jetAuth = jetAuth;
    }

    public HttpRequest.Builder getBuilder(HostEnum host, ApiPathEnum apiPath) {
        return HttpRequest.newBuilder()
                .uri(UriComponentsBuilder.newInstance().scheme(jetProperties.getScheme())
                        .host(host.value)
                        .path(apiPath.value)
                        .build().toUri())
                .headers("Content-Type", "application/json; charset=utf-8")
                .header("Authorization", "Bearer ".concat(jetAuth.getAccessTokenProduto(jetProperties.getProduto()).getAccessToken()));

    }
}
