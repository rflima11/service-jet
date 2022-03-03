package br.com.businesstec.servicejet.config;

import br.com.businesstec.servicejet.datasources.jet.data.Credentials;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "jet")
public class JetProperties {

    private String host;
    private String scheme;
    private String apiPath;
    private String produtoHost;
    private String clienteHost;
    private String pedidoHost;
    private String integrationKey;
    private Credentials produto;
    private Credentials pedido;
    private Credentials cliente;

//    scheme: https://
//    produto-host: adm-produto
//    cliente-host: adm-cliente
//    pedido-host: adm-pedido
//    api-path: -sbx-neo1.plataformaneo.com.br/api/v1
//    integration-key: ${JET_INTEGRATIONKEY:10011446020}

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getIntegrationKey() {
        return integrationKey;
    }

    public String getIntegrationKeyBase64() {
        return Base64.getEncoder().encodeToString(integrationKey.getBytes());
    }

    public void setIntegrationKey(String integrationKey) {
        this.integrationKey = integrationKey;
    }

    public Credentials getProduto() {
        return produto;
    }

    public void setProduto(Credentials produto) {
        this.produto = produto;
    }

    public Credentials getPedido() {
        return pedido;
    }

    public void setPedido(Credentials pedido) {
        this.pedido = pedido;
    }

    public Credentials getCliente() {
        return cliente;
    }

    public void setCliente(Credentials cliente) {
        this.cliente = cliente;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public String getProdutoHost() {
        return produtoHost;
    }

    public void setProdutoHost(String produtoHost) {
        this.produtoHost = produtoHost;
    }

    public String getClienteHost() {
        return clienteHost;
    }

    public void setClienteHost(String clienteHost) {
        this.clienteHost = clienteHost;
    }

    public String getPedidoHost() {
        return pedidoHost;
    }

    public void setPedidoHost(String pedidoHost) {
        this.pedidoHost = pedidoHost;
    }

    public String montarUrlCliente() {
        return this.host.concat(" /produto");
    }

    public String montarUrlPedido() {
        return this.host.concat(" /pedido");
    }

    public String montarUrlProduto() {
        return this.host.concat(" /produto");
    }

//    jet:
//    integration-key: ${JET_INTEGRATIONKEY:10011446020}
//    store-id: ${JET_STOREID:1001144}
//    produto:
//    username: ${JET_PRODUTO_USERNAME:telasramacrisna_admproduto}
//    password: ${JET_PRODUTO_PASSWORD:admprodutotela2@229234F50A}
//    pedido:
//    username: ${JET_PEDIDO_USERNAME:telasramacrisna_admpedido}
//    password: ${JET_PEDIDO_PASSWORD:admpedidotela2@22DB5F08DE}
//    cliente:
//    username: ${JET_CLIENTE_USERNAME:telasramacrisna_admb2b}
//    password: ${JET_CLIENTE_PASSWORD:admb2btela2@227C51240D}
}
