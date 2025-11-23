package com.infnet.tp3.commands;

import lombok.Getter;

@Getter
public class CriarPedidoCommand extends BaseCommand {

    private final String clienteId;
    private final Double valorTotal;

    public CriarPedidoCommand(String id, String clienteId, Double valorTotal) {
        super(id);
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
    }

    @Override
    public String getTipoCommand() {
        return "CriarPedidoCommand";
    }
}