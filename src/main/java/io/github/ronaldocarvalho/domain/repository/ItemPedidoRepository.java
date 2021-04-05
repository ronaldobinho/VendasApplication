package io.github.ronaldocarvalho.domain.repository;

import io.github.ronaldocarvalho.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository <ItemPedido,Integer> {
}
