package io.github.ronaldocarvalho.service;

import io.github.ronaldocarvalho.domain.entity.Pedido;
import io.github.ronaldocarvalho.domain.enums.StatusPedido;
import io.github.ronaldocarvalho.rest.controller.DTO.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto );

    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaPedido(Integer id, StatusPedido statusPedido);
}
