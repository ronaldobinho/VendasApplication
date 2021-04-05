package io.github.ronaldocarvalho.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@AllArgsConstructor
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao")
    private String descricao;


    @Column(name = "preco_unitario")
    private BigDecimal preco;

}
