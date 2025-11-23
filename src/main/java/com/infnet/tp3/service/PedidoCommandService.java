package com.infnet.tp3.service;

import com.infnet.tp3.commands.CriarPedidoCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class PedidoCommandService {

    private final CommandGateway commandGateway;

    public PedidoCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> criarPedido(String clienteId, Double valorTotal) {
        String pedidoId = UUID.randomUUID().toString();
        CriarPedidoCommand command = new CriarPedidoCommand(pedidoId, clienteId, valorTotal);
        return commandGateway.send(command);
    }
}
