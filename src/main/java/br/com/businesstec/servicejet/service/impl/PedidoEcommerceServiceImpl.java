package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.PedidoEcommerce;
import br.com.businesstec.model.entities.StatusPedidoEcommerce;
import br.com.businesstec.model.repository.PedidoEcommerceRepository;
import br.com.businesstec.model.repository.StatusPedidoEcommerceRepository;
import br.com.businesstec.servicejet.client.PedidoJet;
import br.com.businesstec.servicejet.client.dto.PedidoDTO;
import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.enums.EnumEntidadeStrategy;
import br.com.businesstec.servicejet.mapper.StatusPedidoMapper;
import br.com.businesstec.servicejet.service.EntidadeService;
import br.com.businesstec.servicejet.service.PedidoEcommerceService;
import br.com.businesstec.servicejet.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PedidoEcommerceServiceImpl implements PedidoEcommerceService {

    private final PedidoEcommerceRepository repository;
    private final PedidoJet pedidoJet;
    private final TokenService tokenService;
    private final JetProperties jetProperties;
    private final EntidadeService entidadeService;
    private final StatusPedidoEcommerceRepository statusRepository;
    private final StatusPedidoMapper mapper;


    public PedidoEcommerceServiceImpl(PedidoEcommerceRepository repository, PedidoJet pedidoJet, TokenService tokenService, JetProperties jetProperties, EntidadeService entidadeService, StatusPedidoEcommerceRepository statusRepository) {
        this.repository = repository;
        this.pedidoJet = pedidoJet;
        this.tokenService = tokenService;
        this.jetProperties = jetProperties;
        this.entidadeService = entidadeService;
        this.statusRepository = statusRepository;
        mapper = StatusPedidoMapper.INSTANCE;
    }

    @Override
    public PedidoEcommerce salvar(PedidoEcommerce pedido) {
        var entidade = entidadeService.salvarEntidade(EnumEntidadeStrategy.PEDIDO_STRATEGY);
        pedido.setIdEntidade(entidade.getId());
        return repository.save(pedido);
    }

    @Override
    public void alterarStatusPedido(StatusPedidoEcommerce statusPedidoEcommerce) {
        try {
            var token = tokenService.getAccessToken(jetProperties.getPedido());
            var pedidoSalvo = mapper.map(statusPedidoEcommerce);
            Thread.sleep(300);
            var pedido = this.recuperaPedido(statusPedidoEcommerce.getIdentificadorOrigemProduto());
            Thread.sleep(300);
            var isStatusNaoAdicionado = pedido.getHistoryListOrderStatus().stream().noneMatch(orderStatus -> Integer.valueOf(orderStatus.getStatusCode()) >= Integer.valueOf(pedidoSalvo.getStatusCode()));
            if (isStatusNaoAdicionado) {
                var response = pedidoJet.atualizaStatusPedido(token, pedidoSalvo);
                System.out.println(response.getBody());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StatusPedidoEcommerce encontrarPeloIdEntidade(Long idEntidade) {
        return statusRepository.findByIdEntidade(idEntidade).orElseThrow(() -> new RuntimeException("Não encontrado status para esta entidade"));
    }

    public PedidoDTO recuperaPedido(Long idOrder) {
        var token = tokenService.getAccessToken(jetProperties.getPedido());
        return pedidoJet.getPedidoByOrder(token, idOrder).getBody();
    }



}
