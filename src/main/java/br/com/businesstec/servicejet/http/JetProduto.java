package br.com.businesstec.servicejet.http;

import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.client.dto.ProdutoDTO;
import br.com.businesstec.servicejet.client.dto.TokenDTO;
import br.com.businesstec.servicejet.enums.ApiPathEnum;
import br.com.businesstec.servicejet.enums.HostEnum;
import br.com.businesstec.servicejet.exceptions.OkHttpException;
import br.com.businesstec.servicejet.utils.ExceptionConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;
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

    public String salvarProduto(ProdutoDTO produto) {
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

    public TokenDTO getProdutos() {
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
