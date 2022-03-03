package br.com.businesstec.servicejet.datasources.jet.http;

import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.datasources.jet.data.Credentials;
import br.com.businesstec.servicejet.datasources.jet.data.Token;
import br.com.businesstec.servicejet.datasources.jet.exceptions.OkHttpException;
import br.com.businesstec.servicejet.utils.ExceptionConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class JetAuth {

    private static final Logger logger = LogManager.getLogger(JetAuth.class);

    private final JetProperties jetProperties;
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    public JetAuth(JetProperties jetProperties, ObjectMapper objectMapper) {
        this.jetProperties = jetProperties;
        this.objectMapper = objectMapper;
        this.httpClient = HttpClient.newHttpClient();
    }

    public Token getAccessTokenProduto(Credentials credentials) {
        try {
            var credenciais = objectMapper.writeValueAsString(jetProperties.getProduto());
            var request = HttpRequest.newBuilder()
                    .uri(UriComponentsBuilder.newInstance().scheme(jetProperties.getScheme())
                            .host(jetProperties.getProdutoHost())
                            .path(jetProperties.getApiPath().concat("/auth"))
                            .build().toUri())
                    .headers("Content-Type", "application/json; charset=utf-8")
                    .POST(HttpRequest.BodyPublishers.ofString(credenciais))
                    .build();

            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return objectMapper.readValue(getResponseBody(response), Token.class);
        } catch (Exception e) {
            throw new OkHttpException(ExceptionConstants.ERRO_CONVERTER_DTO.getValue() + e.getMessage(), 500);
        }

    }

    private String getResponseBody(HttpResponse<String> response) {
        if (response.statusCode() == 200) {
            return response.body();
        }
        throw new OkHttpException("Não foi possível recuperar o access token", 500);
    }

}
