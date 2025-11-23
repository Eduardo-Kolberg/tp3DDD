package com.infnet.tp3.service;

import com.infnet.tp3.entity.PedidoEntity;
import com.infnet.tp3.events.BaseEvent;
import com.infnet.tp3.repository.PedidoRepository;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoQueryService {

    private final EventStore eventStore;
    private final PedidoRepository pedidoRepository;

    public PedidoQueryService(EventStore eventStore, PedidoRepository pedidoRepository) {
        this.eventStore = eventStore;
        this.pedidoRepository = pedidoRepository;
    }


    public List<BaseEvent> listarTodosEventos() {
        List<BaseEvent> eventos = new ArrayList<>();

        eventStore.readEvents("*").asStream()
                .forEach(eventMessage -> {
                    if (eventMessage.getPayload() instanceof BaseEvent baseEvent) {
                        eventos.add(baseEvent);
                    }
                });

        return eventos;
    }

    public List<BaseEvent> listarEventosPorPedidoId(String pedidoId) {
        List<BaseEvent> eventos = new ArrayList<>();

        DomainEventStream eventStream = eventStore.readEvents(pedidoId);
        while (eventStream.hasNext()) {
            DomainEventMessage<?> eventMessage = eventStream.next();
            if (eventMessage.getPayload() instanceof BaseEvent baseEvent) {
                eventos.add(baseEvent);
            }
        }
        return eventos;
    }

    public Optional<PedidoEntity> obterPedidoPorId(String pedidoId) {
        return pedidoRepository.findById(pedidoId);
    }
}
