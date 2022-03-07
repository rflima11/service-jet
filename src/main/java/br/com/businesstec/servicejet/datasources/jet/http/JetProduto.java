package br.com.businesstec.servicejet.datasources.jet.http;

import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.datasources.jet.data.Credentials;
import br.com.businesstec.servicejet.datasources.jet.data.Token;
import br.com.businesstec.servicejet.datasources.jet.data.enums.ApiPathEnum;
import br.com.businesstec.servicejet.datasources.jet.data.enums.HostEnum;
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
    private final HttpRequestFactory httpRequestFactory;
    private final JetAuth jetAuth;
    private final HttpClient httpClient;

    public JetProduto(JetProperties jetProperties, HttpRequestFactory httpRequestFactory, JetAuth jetAuth) {
        this.jetProperties = jetProperties;
        this.httpRequestFactory = httpRequestFactory;
        this.jetAuth = jetAuth;
        this.httpClient = HttpClient.newHttpClient();
    }

    public Token getProdutos(Credentials credentials) {
        try {
            var request = httpRequestFactory.getBuilder(HostEnum.PRODUTO_HOST, ApiPathEnum.GET_QUEUE_PRODUTOS)
                    .GET()
                    .build();

            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return null;
        } catch (Exception e) {
            throw new OkHttpException(ExceptionConstants.ERRO_CONVERTER_DTO.getValue() + e.getMessage(), 500);
        }

    }
}
