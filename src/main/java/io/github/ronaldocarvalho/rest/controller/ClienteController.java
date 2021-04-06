package io.github.ronaldocarvalho.rest.controller;

import io.github.ronaldocarvalho.domain.entity.Cliente;
import io.github.ronaldocarvalho.domain.repository.ClientesRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClientesRepository clientes;

    public ClienteController(ClientesRepository repository){
        this.clientes = repository;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Cliente>  requestClienteById(@PathVariable Integer id) {
       Optional<Cliente> cliente =  clientes.findById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<Cliente>  salvarCliente(@RequestBody Cliente cliente) {
        Cliente savedCliente = clientes.save(cliente);
        return ResponseEntity.ok(savedCliente);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Cliente>  deleteCliente(@PathVariable Integer id) {
        Optional<Cliente> cliente =  clientes.findById(id);

        if (cliente.isPresent()){
            clientes.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity updateCliente(@PathVariable Integer id,
                                                 @RequestBody Cliente cliente) {
        return clientes.findById(id)
                .map(savedCliente -> {
            cliente.setId(savedCliente.getId());
            clientes.save(cliente);
            return ResponseEntity.noContent().build();
        }).orElseGet( () -> ResponseEntity.notFound().build());
    }



}
