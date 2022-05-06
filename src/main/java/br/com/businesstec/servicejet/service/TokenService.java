package br.com.businesstec.servicejet.service;

import br.com.businesstec.servicejet.client.dto.CredentialsDTO;
import br.com.businesstec.servicejet.config.JetProperties;

public interface TokenService {

    String getAccessToken(CredentialsDTO properties);
}
