package br.com.businesstec.servicejet.model;

import javax.persistence.*;

@Entity
@Table(name = "ctrl_execucao_fluxo_entidade")
public class ControleExecucaoFluxoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "id_ctrl_execucao_fluxo")
    private Long idControleExecucaoFluxo;

    @Column(name = "id_entidade")
    private Long idEntidade;

    @Column(name = "integrado")
    private Boolean integrado;

    public ControleExecucaoFluxoEntidade() {}

    public ControleExecucaoFluxoEntidade(Long idControleExecucaoFluxo, Long idEntidade) {
        this.idControleExecucaoFluxo = idControleExecucaoFluxo;
        this.idEntidade = idEntidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdControleExecucaoFluxo() {
        return idControleExecucaoFluxo;
    }

    public void setIdControleExecucaoFluxo(Long idControleExecucaoFluxo) {
        this.idControleExecucaoFluxo = idControleExecucaoFluxo;
    }

    public Long getIdEntidade() {
        return idEntidade;
    }

    public void setIdEntidade(Long idEntidade) {
        this.idEntidade = idEntidade;
    }

    public Boolean getIntegrado() {
        return integrado;
    }

    public void setIntegrado(Boolean integrado) {
        this.integrado = integrado;
    }
}
