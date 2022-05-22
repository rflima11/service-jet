package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.model.entities.VariacaoItem;
import br.com.businesstec.model.repository.VariacaoItemRepository;
import br.com.businesstec.servicejet.service.VariacaoItemService;
import org.springframework.stereotype.Service;

@Service
public class VariacaoItemServiceImpl implements VariacaoItemService {

    private final VariacaoItemRepository variacaoItemRepository;

    public VariacaoItemServiceImpl(VariacaoItemRepository variacaoItemRepository) {
        this.variacaoItemRepository = variacaoItemRepository;
    }

    @Override
    public VariacaoItem getVariacaoItem(String findByVariacaoItem) {
        return variacaoItemRepository.findByIdentificadorOrigem(findByVariacaoItem).orElseThrow(() -> new RuntimeException("Não encontrada variação Item"));
    }
}
