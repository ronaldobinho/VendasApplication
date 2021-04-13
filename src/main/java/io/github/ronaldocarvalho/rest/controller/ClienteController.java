package io.github.ronaldocarvalho.rest.controller;

import io.github.ronaldocarvalho.domain.entity.Cliente;
import io.github.ronaldocarvalho.domain.repository.ClientesRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClientesRepository clientes;

    public ClienteController(ClientesRepository repository){
        this.clientes = repository;
    }

    @GetMapping("/{id}")
    public Cliente  requestClienteById(@PathVariable Integer id) {
            return clientes
                    .findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Cliente nao encontrado"));
          }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente  salvarCliente(@RequestBody Cliente cliente) {
        return clientes.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Integer id) {
        clientes.findById(id).map(cliente -> {
            clientes.delete(cliente);
            return cliente;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente nao encontrado"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCliente(@PathVariable Integer id,
                              @RequestBody Cliente cliente) {
        clientes.findById(id)
                .map(savedCliente -> {
                    cliente.setId(savedCliente.getId());
                    clientes.save(cliente);
                    return savedCliente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Cliente nao encontrado"));
    }

    @GetMapping("/find")
    public List<Cliente> find (Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);

       return clientes.findAll(example);

    }

}
