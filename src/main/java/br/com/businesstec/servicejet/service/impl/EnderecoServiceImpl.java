package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.Cliente;
import br.com.businesstec.model.entities.Endereco;
import br.com.businesstec.model.repository.EnderecoRepository;
import br.com.businesstec.servicejet.client.dto.AddressDTO;
import br.com.businesstec.servicejet.mapper.EnderecoMapper;
import br.com.businesstec.servicejet.service.EnderecoService;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServiceImpl implements EnderecoService  {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;

    public EnderecoServiceImpl(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
        enderecoMapper = EnderecoMapper.INSTANCE;
    }

    @Override
    public Endereco salvarEndereco(AddressDTO enderecoDto, Cliente cliente) {

        var endereco = enderecoMapper.map(enderecoDto);
        endereco.setIdCliente(cliente.getId());

        var enderecoOptional = enderecoRepository.findByIdentificadorOrigem(endereco.getIdentificadorOrigem());

        if (enderecoOptional.isPresent()) {
            var enderecoSalvo = enderecoOptional.get();
            endereco.setId(enderecoSalvo.getId());
        }
        return enderecoRepository.save(endereco);
    }
}
