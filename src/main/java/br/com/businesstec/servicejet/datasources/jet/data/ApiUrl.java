package br.com.businesstec.servicejet.datasources.jet.data;

public class ApiUrl {

    private String scheme;
    private String produtoHost;
    private String clienteHost;
    private String pedidoHost;
    private String apiPath;

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public void setProdutoHost(String produtoHost) {
        this.produtoHost = produtoHost;
    }

    public void setClienteHost(String clienteHost) {
        this.clienteHost = clienteHost;
    }

    public void setPedidoHost(String pedidoHost) {
        this.pedidoHost = pedidoHost;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    //    scheme: https://
//    produto-host: adm-produto
//    cliente-host: adm-cliente
//    pedido-host: adm-pedido
//    api-path: -sbx-neo1.plataformaneo.com.br/api/v1
//    integration-key: ${JET_INTEGRATIONKEY:10011446020}
}
