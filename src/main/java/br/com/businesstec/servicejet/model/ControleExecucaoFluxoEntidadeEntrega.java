package br.com.businesstec.servicejet.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ctrl_execucao_fluxo_entidade_entrega")
public class ControleExecucaoFluxoEntidadeEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "id_ctrl_execucao_fluxo_entidade")
    private Long idControleExecucaoFluxoEntidade;
    @Column(name = "data_hora")
    private LocalDateTime dataHora;
    @Column(name = "erro")
    private Boolean erro;
    @Column(name = "descricao_erro")
    private String descricaoErro;

    public ControleExecucaoFluxoEntidadeEntrega() {}

    public ControleExecucaoFluxoEntidadeEntrega(Long idControleExecucaoFluxoEntidade, Boolean erro) {
        this.idControleExecucaoFluxoEntidade = idControleExecucaoFluxoEntidade;
        this.erro = erro;
        this.dataHora = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdControleExecucaoFluxoEntidade() {
        return idControleExecucaoFluxoEntidade;
    }

    public void setIdControleExecucaoFluxoEntidade(Long idControleExecucaoFluxoEntidade) {
        this.idControleExecucaoFluxoEntidade = idControleExecucaoFluxoEntidade;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Boolean getErro() {
        return erro;
    }

    public void setErro(Boolean erro) {
        this.erro = erro;
    }

    public String getDescricaoErro() {
        return descricaoErro;
    }

    public void setDescricaoErro(String descricaoErro) {
        this.descricaoErro = descricaoErro;
    }
}
