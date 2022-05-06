package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.Cliente;
import br.com.businesstec.model.entities.Entidade;
import br.com.businesstec.model.repository.ClienteRepository;
import br.com.businesstec.servicejet.client.AuthClienteJet;
import br.com.businesstec.servicejet.client.ClienteJet;
import br.com.businesstec.servicejet.client.dto.ClienteDTO;
import br.com.businesstec.servicejet.client.dto.Queue;
import br.com.businesstec.servicejet.config.JetProperties;
import br.com.businesstec.servicejet.enums.EnumEntidadeStrategy;
import br.com.businesstec.servicejet.service.ClienteService;
import br.com.businesstec.servicejet.service.EnderecoService;
import br.com.businesstec.servicejet.service.EntidadeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteJet clienteJet;
    private final AuthClienteJet authClienteJet;
    private final JetProperties jetProperties;
    private final ClienteRepository clienteRepository;
    private final EnderecoService enderecoService;
    private final EntidadeService entidadeService;

    public ClienteServiceImpl(ClienteJet clienteJet, AuthClienteJet authClienteJet, JetProperties jetProperties, ClienteRepository clienteRepository, EnderecoService enderecoService, EntidadeService entidadeService) {
        this.clienteJet = clienteJet;
        this.authClienteJet = authClienteJet;
        this.jetProperties = jetProperties;
        this.clienteRepository = clienteRepository;
        this.enderecoService = enderecoService;
        this.entidadeService = entidadeService;
    }

    @Override
    public List<Queue<ClienteDTO>> recuperarQueueClientes() {
        return clienteJet.getClientes("Bearer ".concat(authClienteJet.auth(jetProperties.getCliente()).getBody().getAccessToken())).getBody();
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        var optionalCliente = clienteRepository.findByIdentificadorOrigem(cliente.getIdentificadorOrigem());
        if (optionalCliente.isPresent()) {
            var clienteSalvo = optionalCliente.get();
            cliente.setId(clienteSalvo.getId());
        }
        var entidade = entidadeService.salvarEntidade(EnumEntidadeStrategy.CLIENTES_STRATEGY);
        cliente.setIdEntidade(entidade.getId());
        var novoCliente = clienteRepository.save(cliente);
        return novoCliente;
    }
}
