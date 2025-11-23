package com.infnet.tp3.commands;

public class AdicionarItemCommand extends BaseCommand {

    private final String produtoId;
    private final int quantidade;
    private final double valorUnitario; // novo campo

    public AdicionarItemCommand(String pedidoId, String produtoId, int quantidade, double valorUnitario) {
        super(pedidoId); // ID do agregado
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public String getProdutoId() {
        return produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    @Override
    public String getTipoCommand() {
        return "AdicionarItemCommand";
    }
}
