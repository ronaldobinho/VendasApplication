package io.github.ronaldocarvalho.rest.controller;

import io.github.ronaldocarvalho.domain.entity.ItemPedido;
import io.github.ronaldocarvalho.domain.entity.Pedido;
import io.github.ronaldocarvalho.domain.enums.StatusPedido;
import io.github.ronaldocarvalho.rest.controller.DTO.AtualizacaoStatusPedidoDTO;
import io.github.ronaldocarvalho.rest.controller.DTO.InformacoesItemPedidoDTO;
import io.github.ronaldocarvalho.rest.controller.DTO.InformacoesPedidoDTO;
import io.github.ronaldocarvalho.rest.controller.DTO.PedidoDTO;
import io.github.ronaldocarvalho.service.PedidoService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @PatchMapping({"id"})
    public void updateStatus(@PathVariable Integer id,
                             @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        service.atualizaPedido(id, StatusPedido.valueOf(novoStatus));

    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return service
                .obterPedidoCompleto(id)
                .map(this::converterPedidosDtop)
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Pedido não encontrado."));
    }

    private InformacoesPedidoDTO converterPedidosDtop(Pedido pedido){
        return InformacoesPedidoDTO.builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format((DateTimeFormatter.ofPattern("dd/MM/yyy"))))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .status(pedido.getStatusPedido().name())
                .total(pedido.getTotal())
                .items(converter(pedido.getItens()))
                .build();
    }

    private List<InformacoesItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> InformacoesItemPedidoDTO
                        .builder().descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }
}
