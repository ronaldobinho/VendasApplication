package io.github.ronaldocarvalho.service;

import io.github.ronaldocarvalho.domain.entity.Pedido;
import io.github.ronaldocarvalho.rest.controller.DTO.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto );

}
