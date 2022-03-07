package br.com.businesstec.servicejet.datasources.jet.http;

import br.com.businesstec.servicejet.datasources.jet.data.Marca;
import br.com.businesstec.servicejet.datasources.jet.data.enums.ApiPathEnum;
import br.com.businesstec.servicejet.datasources.jet.data.enums.HostEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class JetMarca {

    private final HttpRequestFactory httpRequestFactory;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public JetMarca(HttpRequestFactory httpRequestFactory, ObjectMapper objectMapper) {
        this.httpRequestFactory = httpRequestFactory;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = objectMapper;
    }

    public String integrarMarca(Marca marca) {
        try {
            var body = objectMapper.writeValueAsString(marca);
            var request = httpRequestFactory.getBuilder(HostEnum.PRODUTO_HOST, ApiPathEnum.INSERIR_MARCA)
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {

                return response.body();
            }
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
