package br.com.businesstec.servicejet.service;

import br.com.businesstec.model.entities.Entidade;
import br.com.businesstec.servicejet.enums.EnumEntidadeStrategy;

public interface EntidadeService {

    Entidade salvarEntidade(EnumEntidadeStrategy enumEntidade);
}
