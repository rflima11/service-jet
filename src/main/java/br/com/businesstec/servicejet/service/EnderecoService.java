package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.Cliente;
import br.com.businesstec.model.entities.Endereco;
import br.com.businesstec.servicejet.client.dto.AddressDTO;

public interface EnderecoService {

    Endereco salvarEndereco(AddressDTO endereco, Cliente cliente);
}
