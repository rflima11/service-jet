package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.MarcaEcommerce;
import br.com.businesstec.model.repository.MarcaEcommerceRepository;
import br.com.businesstec.servicejet.service.MarcaEcommerceService;
import org.springframework.stereotype.Service;

@Service
public class MarcaEcommerceServiceImpl implements MarcaEcommerceService {

    private final MarcaEcommerceRepository marcaEcommerceRepository;

    public MarcaEcommerceServiceImpl(MarcaEcommerceRepository marcaEcommerceRepository) {
        this.marcaEcommerceRepository = marcaEcommerceRepository;
    }

    @Override
    public MarcaEcommerce encontrarPeloIdMarca(Long idMarca) {
        return marcaEcommerceRepository.findByIdMarca(idMarca).orElseThrow(() -> new RuntimeException("Marca não encontrada ID: " + idMarca));
    }
}
