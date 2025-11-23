package com.infnet.tp3.repository;

import com.infnet.tp3.constants.StatusPedido;
import com.infnet.tp3.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntity, String> {
    List<PedidoEntity> findByClienteId(String clienteId);

    List<PedidoEntity> findByStatus(StatusPedido status);

}
