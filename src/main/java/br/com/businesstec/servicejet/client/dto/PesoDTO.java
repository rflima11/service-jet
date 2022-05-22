package br.com.businesstec.servicejet.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PesoDTO {

    @JsonProperty("height")
    private Double altura;
    @JsonProperty("width")
    private Double largura;
    @JsonProperty("length")
    private Double comprimento;
    @JsonProperty("diameter")
    private Double diametro;
    @JsonProperty("weight")
    private Double peso;

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getLargura() {
        return largura;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
    }

    public Double getComprimento() {
        return comprimento;
    }

    public void setComprimento(Double tamanho) {
        this.comprimento = tamanho;
    }

    public Double getDiametro() {
        return diametro;
    }

    public void setDiametro(Double diametro) {
        this.diametro = diametro;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
}
