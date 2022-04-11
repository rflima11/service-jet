package br.com.businesstec.servicejet.model;

import javax.persistence.*;

@Entity
@Table(name = "variacao")
public class Variacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identificador_origem")
    private String identificadorOrigem;

    @Column(name = "id_entidade")
    private Long idEntidade;

    @Column(name = "descricao")
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificadorOrigem() {
        return identificadorOrigem;
    }

    public void setIdentificadorOrigem(String identificadorOrigem) {
        this.identificadorOrigem = identificadorOrigem;
    }

    public Long getIdEntidade() {
        return idEntidade;
    }

    public void setIdEntidade(Long idEntidade) {
        this.idEntidade = idEntidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
