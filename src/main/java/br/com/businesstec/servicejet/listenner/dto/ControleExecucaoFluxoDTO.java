package br.com.businesstec.servicejet.listenner.dto;

import java.time.LocalDateTime;

public class ControleExecucaoFluxoDTO {

    private Long id;
    private Long idControleFluxo;
    private LocalDateTime dataHora;
    private Boolean erro;
    private String descricaoErro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdControleFluxo() {
        return idControleFluxo;
    }

    public void setIdControleFluxo(Long idControleFluxo) {
        this.idControleFluxo = idControleFluxo;
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

    //    {"id":111926,"idControleFluxo":1,"dataHora":[2022,4,17,1,15,45,970661000],"erro":false,"descricaoErro":null}
}
