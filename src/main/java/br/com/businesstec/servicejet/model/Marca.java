package br.com.businesstec.servicejet.model;

import javax.persistence.*;

@Entity
@Table(name = "marca_ecommerce")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "id_marca")
    private Long idMarca;
    @Column(name = "id_marca_jet")
    private Long idMarcaJet;
    @Column(name = "em_destaque")
    private Boolean emDestaque;
    @Column(name = "ativo")
    private Boolean ativo;
    @Column(name = "imagem")
    private String imagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
    }

    public Long getIdMarcaJet() {
        return idMarcaJet;
    }

    public void setIdMarcaJet(Long idMarcaJet) {
        this.idMarcaJet = idMarcaJet;
    }

    public Boolean getEmDestaque() {
        return emDestaque;
    }

    public void setEmDestaque(Boolean emDestaque) {
        this.emDestaque = emDestaque;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
