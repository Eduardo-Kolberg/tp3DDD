package com.infnet.tp3.entity;

import com.infnet.tp3.constants.StatusPedido;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
public class PedidoEntity {

    @Id
    private String id;

    private String clienteId;

    private Double valorTotal;

    @ElementCollection
    @CollectionTable(name = "pedido_itens", joinColumns = @JoinColumn(name = "pedido_id"))
    @Column(name = "produto_id")
    private List<String> itens = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    public PedidoEntity() {
    }

    public PedidoEntity(String id, String clienteId, Double valorTotal, StatusPedido status) {
        this.id = id;
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
        this.status = status;
        this.itens = new ArrayList<>();
    }
}
