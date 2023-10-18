package br.com.schiavon.food.domain.models.enuns;

public enum StatusPedido {
    CRIADO(null),
    CONFIRMADO(CRIADO),
    ENTREGUE(CONFIRMADO),
    CANCELADO(CRIADO);

    private StatusPedido statusPermitido;

    StatusPedido(StatusPedido statusPermitido) {
        this.statusPermitido = statusPermitido;
    }

    public boolean verificaAlteracaoStatus(StatusPedido statusPedidoNovo){
        return statusPedidoNovo.statusPermitido.equals(this);
    }

}
