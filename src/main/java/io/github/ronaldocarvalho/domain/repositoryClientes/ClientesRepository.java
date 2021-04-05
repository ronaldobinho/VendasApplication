package io.github.ronaldocarvalho.domain.repositoryClientes;

import io.github.ronaldocarvalho.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeLike(String nome);
}
