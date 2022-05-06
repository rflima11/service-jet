package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.servicejet.client.AuthClienteJet;
import br.com.businesstec.servicejet.client.dto.CredentialsDTO;
import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.service.TokenService;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    private final JetProperties jetProperties;
    private final AuthClienteJet authClienteJet;

    public TokenServiceImpl(JetProperties jetProperties, AuthClienteJet authClienteJet) {
        this.jetProperties = jetProperties;
        this.authClienteJet = authClienteJet;
    }

    @Override
    public String getAccessToken(CredentialsDTO credentialsDTO) {
        return "Bearer ".concat(authClienteJet.auth(credentialsDTO).getBody().getAccessToken());
    }
}
