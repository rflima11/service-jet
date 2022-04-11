package br.com.businesstec.servicejet.repository;

import br.com.businesstec.servicejet.model.ControleExecucaoFluxoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ControleExecucaoFluxoEntidadeRepository extends JpaRepository<ControleExecucaoFluxoEntidade, Long> {

    @Query(value = "select cf.id_tipo_entidade  \n" +
            "from ctrl_fluxo cf \n" +
            "join ctrl_execucao_fluxo cef on cf.id = cef.id_ctrl_fluxo \n" +
            "join ctrl_execucao_fluxo_entidade cefe on cefe.id_ctrl_execucao_fluxo = cef.id \n" +
            "where cefe.id = :controleFluxoId", nativeQuery = true)
    Long findTipoEntidadePorCtrlFluxo(@Param("controleFluxoId") Long controleFluxoId);

    List<ControleExecucaoFluxoEntidade> findByIntegradoFalse();

}
