package com.infnet.tp3.events;

import lombok.Getter;

import java.time.Instant;

@Getter
public abstract class BaseEvent {

    private final String id;
    private final Instant dataOperacao;

    protected BaseEvent(String id) {
        this.id = id;
        this.dataOperacao = Instant.now();
    }

    public abstract String getTipoEvento();
}
