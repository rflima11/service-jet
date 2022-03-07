package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.servicejet.model.Marca;
import br.com.businesstec.servicejet.repository.MarcaRepository;
import br.com.businesstec.servicejet.service.MarcaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaServiceImpl implements MarcaService {

    private final MarcaRepository marcaRepository;

    public MarcaServiceImpl(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @Override
    public List<Marca> recuperarMarcasNaoIntegradas() {
        return marcaRepository.findByIdMarcaJetIsNull();
    }

    @Override
    public List<Marca> recuperarTodasMarcas() {
        return marcaRepository.findByIdMarcaJetIsNotNull();
    }

    @Override
    public Marca salvarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }
}
