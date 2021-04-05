package io.github.ronaldocarvalho;

import io.github.ronaldocarvalho.domain.entity.Cliente;
import io.github.ronaldocarvalho.domain.entity.Pedido;
import io.github.ronaldocarvalho.domain.repository.ClientesRepository;
import io.github.ronaldocarvalho.domain.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init (@Autowired ClientesRepository clientesRepository,
                                               @Autowired PedidosRepository pedidosRepository) {


        return args -> {
            System.out.println("Salvando Clientes");
          Cliente cliente1 = new Cliente("Ronaldo");
          clientesRepository.save(cliente1);

            Pedido pedido = new Pedido();
            pedido.setCliente(cliente1);
            pedido.setDataPedido(LocalDate.now());
            pedido.setTotal(BigDecimal.valueOf(1000));

            pedidosRepository.save(pedido);

            pedidosRepository.findByCliente(cliente1).forEach(System.out::println);

        };
    }
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
