package br.com.businesstec.servicejet.model;

import javax.persistence.*;

@Entity
@Table(name = "variacao_item")
public class VariacaoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identificador_origem")
    private String identificadorOrigem;

    @Column(name = "id_variacao")
    private Long idVariacao;

    @Column(name = "descricao")
    private String descricao;

    public VariacaoItem() { this.idVariacao = 1L; }

    public VariacaoItem(String identificadorOrigem, String descricao) {
        this.identificadorOrigem = identificadorOrigem;
        this.descricao = descricao;
        this.idVariacao = 1L;
    }

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

    public Long getIdVariacao() {
        return idVariacao;
    }

    public void setIdVariacao(Long idVariacao) {
        this.idVariacao = idVariacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
