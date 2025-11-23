package com.infnet.tp3.events;

import lombok.Getter;

@Getter
public class PedidoCriadoEvent extends BaseEvent {

    private final String clienteId;
    private final Double valorTotal;

    public PedidoCriadoEvent(String id, String clienteId, Double valorTotal) {
        super(id);
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
    }

    @Override
    public String getTipoEvento() {
        return "PedidoCriadoEvent";
    }
}
