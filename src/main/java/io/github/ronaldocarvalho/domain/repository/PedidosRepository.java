package io.github.ronaldocarvalho.domain.repository;

import io.github.ronaldocarvalho.domain.entity.Cliente;
import io.github.ronaldocarvalho.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

}
