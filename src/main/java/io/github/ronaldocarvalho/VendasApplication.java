package io.github.ronaldocarvalho;

import io.github.ronaldocarvalho.domain.Cliente;
import io.github.ronaldocarvalho.domain.repositoryClientes.ClientesRepository;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init (@Autowired ClientesRepository repository){
        return args -> {
            System.out.println("Salvando Clientes");
            repository.save(new Cliente("Ronaldo"));
            repository.save(new Cliente("Teste"));
            repository.save(new Cliente("Teste1"));


            System.out.println("Buscando todos Clientes");
            List<Cliente> allClientes = repository.findAll();
            allClientes.forEach(System.out::println);


            System.out.println("Atualizando todos Clientes");
            allClientes.forEach(c -> {
                c.setNome(c.getNome()+ "atualizado");
                repository.save(c);

            });


            System.out.println("Buscar por nome todos Clientes");
            repository.findByNomeLike("Ronaldo");


            System.out.println("Deletando todos Clientes");
            repository.findAll().forEach(repository::delete);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
