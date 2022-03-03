package br.com.businesstec.servicejet.datasources.jet.http;

import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.datasources.jet.data.Credentials;
import br.com.businesstec.servicejet.datasources.jet.data.Token;
import br.com.businesstec.servicejet.datasources.jet.data.enums.ApiPathEnum;
import br.com.businesstec.servicejet.datasources.jet.exceptions.OkHttpException;
import br.com.businesstec.servicejet.utils.ExceptionConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class JetProduto {
    private static final Logger logger = LogManager.getLogger(JetProduto.class);

    private final JetProperties jetProperties;
    private final JetAuth jetAuth;
    private final HttpClient httpClient;

    public JetProduto(JetProperties jetProperties, JetAuth jetAuth) {
        this.jetProperties = jetProperties;
        this.jetAuth = jetAuth;
        this.httpClient = HttpClient.newHttpClient();
    }

    public Token getProdutos(Credentials credentials) {
        try {
            var request = HttpRequest.newBuilder()
                    .uri(UriComponentsBuilder.newInstance().scheme(jetProperties.getScheme())
                            .host(jetProperties.getProdutoHost())
                            .path(jetProperties.getApiPath())
                            .path(ApiPathEnum.GET_QUEUE_PRODUTOS.getValue())
                            .queryParam("integrationKey", jetProperties.getIntegrationKeyBase64())
                            .build()
                            .toUri()
                            )
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("Authorization", "Bearer ".concat(jetAuth.getAccessTokenProduto(jetProperties.getProduto()).getAccessToken()))
                    .GET()
                    .build();

            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return null;
        } catch (Exception e) {
            throw new OkHttpException(ExceptionConstants.ERRO_CONVERTER_DTO.getValue() + e.getMessage(), 500);
        }

    }
}
