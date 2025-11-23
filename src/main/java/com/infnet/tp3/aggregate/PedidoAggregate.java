package com.infnet.tp3.aggregate;

import com.infnet.tp3.commands.AdicionarItemCommand;
import com.infnet.tp3.commands.CriarPedidoCommand;
import com.infnet.tp3.constants.StatusPedido;
import com.infnet.tp3.events.ItemAdicionadoEvent;
import com.infnet.tp3.events.PedidoCriadoEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

@Aggregate
public class PedidoAggregate {

    @AggregateIdentifier
    private String id;
    private String clienteId;
    private Double valorTotal;
    private List<String> itens;
    private StatusPedido status;


    protected PedidoAggregate() {
    }

    @CommandHandler
    public PedidoAggregate(CriarPedidoCommand command) {
        if (command.getValorTotal() <= 0) {
            throw new IllegalArgumentException("O valor não pode ser negativo ou zero.");
        }
        AggregateLifecycle.apply(new PedidoCriadoEvent(
                command.getId(),
                command.getClienteId(),
                command.getValorTotal()
        ));
    }

    @EventSourcingHandler
    public void on(PedidoCriadoEvent event) {
        this.id = event.getId();
        this.clienteId = event.getClienteId();
        this.valorTotal = event.getValorTotal();
        this.itens = new ArrayList<>(); // inicia a lista vazia
        this.status = StatusPedido.CRIADO; // status inicial
    }

    @CommandHandler
    public void handle(AdicionarItemCommand command) {
        if (command.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        AggregateLifecycle.apply(new ItemAdicionadoEvent(
                this.id,
                command.getProdutoId(),
                command.getQuantidade(),
                command.getValorUnitario()
        ));
    }

    @EventSourcingHandler
    public void on(ItemAdicionadoEvent event) {
        this.itens.add(event.getProdutoId());
        this.valorTotal += event.getQuantidade() * event.getValorUnitario();
    }
}
