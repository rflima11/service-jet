package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.servicejet.client.PedidoJet;
import br.com.businesstec.servicejet.client.dto.PedidoDTO;
import br.com.businesstec.servicejet.client.dto.Queue;
import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.http.JetAuth;
import br.com.businesstec.servicejet.service.PedidoService;
import br.com.businesstec.servicejet.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoJet pedidoJet;
    private final JetAuth jetAuth;
    private final TokenService tokenService;
    private final JetProperties jetProperties;

    public PedidoServiceImpl(PedidoJet pedidoJet, JetAuth jetAuth, TokenService tokenService, JetProperties jetProperties) {
        this.pedidoJet = pedidoJet;
        this.jetAuth = jetAuth;
        this.tokenService = tokenService;
        this.jetProperties = jetProperties;
    }

    @Override
    public List<Queue<PedidoDTO>> recuperarQueuePedidos() {
        return pedidoJet.getPedidos(tokenService.getAccessToken(jetProperties.getPedido())).getBody();
    }

}
