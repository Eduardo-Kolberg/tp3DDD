package com.infnet.tp3.controller;

import com.infnet.tp3.commands.CriarPedidoCommand;
import com.infnet.tp3.entity.PedidoEntity;
import com.infnet.tp3.events.BaseEvent;
import com.infnet.tp3.service.PedidoQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Criar e consultar pedidos")
public class PedidoController {

    private final CommandGateway commandGateway;
    private final PedidoQueryService queryService;

    public PedidoController(CommandGateway commandGateway, PedidoQueryService queryService) {
        this.commandGateway = commandGateway;
        this.queryService = queryService;
    }

    @PostMapping("/criar")
    @Operation(
            summary = "Cria um novo pedido",
            description = "Cria um pedido no sistema."
    )
    public CompletableFuture<ResponseEntity<String>> criarPedido(
            @Parameter(description = "ID único do pedido") @RequestParam String pedidoId,
            @Parameter(description = "ID do cliente que está realizando o pedido") @RequestParam String clienteId,
            @Parameter(description = "Valor total inicial do pedido") @RequestParam Double valorTotal
    ) {

        CriarPedidoCommand command = new CriarPedidoCommand(
                pedidoId,
                clienteId,
                valorTotal
        );

        return commandGateway.send(command)
                .thenApply(result -> ResponseEntity.ok("Pedido criado!"))
                .exceptionally(ex -> ResponseEntity.badRequest().body("Erro: " + ex.getMessage()));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Lista todos os eventos de todos os pedidos")
    public ResponseEntity<List<BaseEvent>> listarTodosEventos() {
        List<BaseEvent> eventos = queryService.listarTodosEventos();
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{pedidoId}/eventos")
    @Operation(summary = "Lista todos os eventos de um pedido por ID")
    public ResponseEntity<List<BaseEvent>> listarEventosPorPedido(@Parameter(description = "ID do pedido") @PathVariable String pedidoId) {
        List<BaseEvent> eventos = queryService.listarEventosPorPedidoId(pedidoId);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{pedidoId}")
    @Operation(summary = "Obtém um pedido pelo ID")
    public ResponseEntity<PedidoEntity> obterPedidoPorId(@Parameter(description = "ID do pedido") @PathVariable String pedidoId) {
        Optional<PedidoEntity> pedido = queryService.obterPedidoPorId(pedidoId);
        return pedido.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

