package org.example;

import org.example.model.Client;
import org.example.model.Product;
import org.example.model.Role;
import org.example.repository.ClientRepository;
import org.example.repository.ProductRepository;
import org.example.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan("org.example.*")
@EnableJpaRepositories(basePackages = "org.example.repository")
@EntityScan("org.example.model")
@EnableTransactionManagement
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner run(ProductRepository productRepository, RoleRepository roleRepository, ClientRepository clientRepository, PasswordEncoder passwordEncoder){
        return args -> {
            Product product1 = new Product("iPhone 11", 1000, 10);
            Product product2 = new Product("iPhone 15", 1200, 5);
            productRepository.save(product1);
            productRepository.save(product2);

            Role admin = new Role();
            admin.setName("ROLE_ADMIN");
            roleRepository.save(admin);

            Role client = new Role();
            client.setName("ROLE_USER");
            roleRepository.save(client);

            List<Role> roles_for_admin = new ArrayList<>();
            roles_for_admin.add(admin);
            roles_for_admin.add(client);

            List<Role> roles_for_user = new ArrayList<>();
            roles_for_user.add(client);

            Client administrator = new Client();
            administrator.setUsername("admin");
            administrator.setPassword(passwordEncoder.encode("admin"));
            administrator.setRoles(roles_for_admin);

            Client user = new Client();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setRoles(roles_for_user);

            clientRepository.save(administrator);
            clientRepository.save(user);
        };
    }
}