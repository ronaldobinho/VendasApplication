package io.github.ronaldocarvalho.rest.controller;

import io.github.ronaldocarvalho.domain.entity.Pedido;
import io.github.ronaldocarvalho.rest.controller.DTO.PedidoDTO;
import io.github.ronaldocarvalho.service.PedidoService;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save( @RequestBody PedidoDTO dto ){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }
}
