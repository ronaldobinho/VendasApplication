package io.github.ronaldocarvalho.domain.repository;

import io.github.ronaldocarvalho.domain.entity.Cliente;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {
    @Query(value = " select c from Cliente c where c.nome like '%:nome%'")
    List<Cliente> findByNomeLike(@Param("nome") String nome);


    @Query(value = " delete from Cliente c where c.nome = :nome")
    List<Cliente> deleteByNome(String nome);

    boolean existsByNome(String nome);

    @Query(value = "select c from Cliente c left join fetch c.pedidos where c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);
}
