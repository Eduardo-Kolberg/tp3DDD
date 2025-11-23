package com.infnet.tp3.events;

public class ItemAdicionadoEvent extends BaseEvent {

    private final String produtoId;
    private final int quantidade;
    private final double valorUnitario;

    public ItemAdicionadoEvent(String pedidoId, String produtoId, int quantidade, double valorUnitario) {
        super(pedidoId);
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public String getProdutoId() { return produtoId; }
    public int getQuantidade() { return quantidade; }
    public double getValorUnitario() { return valorUnitario; }

    @Override
    public String getTipoEvento() {
        return "ItemAdicionadoEvent";
    }
}
