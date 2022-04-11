package br.com.businesstec.servicejet.repository;

import br.com.businesstec.servicejet.model.Variacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VariacaoRepository extends JpaRepository<Variacao, Long> {

    List<Variacao> findByIdentificadorOrigem(String identificadorOrigem);
}
