package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.Entidade;
import br.com.businesstec.model.repository.EntidadeRepository;
import br.com.businesstec.servicejet.enums.EnumEntidadeStrategy;
import br.com.businesstec.servicejet.service.EntidadeService;
import org.springframework.stereotype.Service;

@Service
public class EntidadeServiceImpl implements EntidadeService {

    private final EntidadeRepository entidadeRepository;

    public EntidadeServiceImpl(EntidadeRepository entidadeRepository) {
        this.entidadeRepository = entidadeRepository;
    }

    @Override
    public Entidade salvarEntidade(EnumEntidadeStrategy enumEntidade) {
        var entidade = new Entidade();
        entidade.setIdEntidade(enumEntidade.getValue());
        return entidadeRepository.save(entidade);
    }

    @Override
    public Entidade encontrarIdEntidade(Long idEntidade) {
        return entidadeRepository.findById(idEntidade).orElseThrow(() -> new RuntimeException("Não encontrada entidade"));
    }


}
