package br.com.businesstec.servicejet.config;

import br.com.businesstec.servicejet.client.dto.CredentialsDTO;
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
    private CredentialsDTO produto;
    private CredentialsDTO pedido;
    private CredentialsDTO cliente;

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

    public CredentialsDTO getProduto() {
        return produto;
    }

    public void setProduto(CredentialsDTO produto) {
        this.produto = produto;
    }

    public CredentialsDTO getPedido() {
        return pedido;
    }

    public void setPedido(CredentialsDTO pedido) {
        this.pedido = pedido;
    }

    public CredentialsDTO getCliente() {
        return cliente;
    }

    public void setCliente(CredentialsDTO cliente) {
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

}
