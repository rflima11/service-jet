package br.com.businesstec.servicejet.client.config;

import br.com.businesstec.servicejet.client.AuthClienteJet;
import br.com.businesstec.servicejet.client.dto.CredentialsDTO;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class CustomErrorDecoder implements ErrorDecoder {




    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());
        Response.Body responseBody = response.body();
        if (responseStatus.is4xxClientError()) {
            System.out.println(responseBody);

        }
        return new Exception(responseBody.toString());
    }
}
