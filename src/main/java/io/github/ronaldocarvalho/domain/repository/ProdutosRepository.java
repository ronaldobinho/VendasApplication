package io.github.ronaldocarvalho.domain.repository;

import io.github.ronaldocarvalho.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produto, Integer> {
}
