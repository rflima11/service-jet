package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.ControleFluxo;
import br.com.businesstec.model.repository.ControleFluxoRepository;
import br.com.businesstec.servicejet.service.ControleFluxoService;
import org.springframework.stereotype.Service;

@Service
public class ControleFluxoServiceImpl implements ControleFluxoService {

    private final ControleFluxoRepository controleFluxoRepository;

    public ControleFluxoServiceImpl(ControleFluxoRepository controleFluxoRepository) {
        this.controleFluxoRepository = controleFluxoRepository;
    }

    @Override
    public ControleFluxo recuperarFluxoPeloId(Long id) {
        var optionalControleFluxo = controleFluxoRepository.findById(id);
        return optionalControleFluxo.orElseThrow(() -> new RuntimeException("Fluxo não encontrado"));
    }
}