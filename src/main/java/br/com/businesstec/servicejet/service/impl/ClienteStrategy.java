package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleExecucaoFluxo;
import br.com.businesstec.servicejet.enums.EnumEntidadeStrategy;
import br.com.businesstec.servicejet.listenner.event.ControleExecucaoFluxoEvent;
import br.com.businesstec.servicejet.mapper.ClienteMapper;
import br.com.businesstec.servicejet.mapper.EnderecoMapper;
import br.com.businesstec.servicejet.service.ClienteService;
import br.com.businesstec.servicejet.service.ControleExecucaoFluxoEntidadeService;
import br.com.businesstec.servicejet.service.EnderecoService;
import br.com.businesstec.servicejet.service.EntidadeStrategy;
import org.springframework.stereotype.Service;

@Service
public class ClienteStrategy implements EntidadeStrategy {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;
    private final EnderecoService enderecoService;
    private final EnderecoMapper enderecoMapper;
    private final ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService;

    public ClienteStrategy(ClienteService clienteService, EnderecoService enderecoService, ControleExecucaoFluxoEntidadeService controleExecucaoFluxoEntidadeService) {
        this.clienteService = clienteService;
        this.enderecoService = enderecoService;
        this.controleExecucaoFluxoEntidadeService = controleExecucaoFluxoEntidadeService;
        clienteMapper = ClienteMapper.INSTANCE;
        enderecoMapper = EnderecoMapper.INSTANCE;
    }


    @Override
    public void executar(ControleExecucaoFluxo controleExecucaoFluxo) {
        var fila = clienteService.recuperarQueueClientes();
        fila.stream().forEach(f -> {
            var clienteDto = f.getEntity();
            var clienteModel = clienteMapper.map(clienteDto);
            var clienteSalvo = clienteService.salvar(clienteModel, controleExecucaoFluxo, f.getIdQueue());

            if (!clienteDto.getAdress().isEmpty()) {
                clienteDto.getAdress().stream().forEach(e -> {
                    enderecoService.salvarEndereco(e, clienteSalvo);
                });
            }


        });
    }

    @Override
    public EnumEntidadeStrategy getEntidadeStrategy() {
        return EnumEntidadeStrategy.CLIENTES_STRATEGY;
    }
}
