package com.example.sumativa1seguridad;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.sumativa1seguridad.Entity.User;
import com.example.sumativa1seguridad.Repository.UserRepository;



@SpringBootApplication
public class Sumativa1seguridadApplication {
    public static void main(String[] args) {
        SpringApplication.run(Sumativa1seguridadApplication.class, args);
    }

    @Bean
    CommandLineRunner seedUsers(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("diego") == null) {
                var u = new User();
                u.setUsername("diego");
                u.setEmail("dgtvillalobos@gmail.com");
                u.setPassword(encoder.encode("q1w2e3###"));
                repo.save(u);
            }
            if (repo.findByUsername("javiera") == null) {
                var u = new User();
                u.setUsername("javiera");
                u.setEmail("javiera@gmail.com");
                u.setPassword(encoder.encode("q1w2e3###"));
                repo.save(u);
            }
            if (repo.findByUsername("admin") == null) {
                var u = new User();
                u.setUsername("admin");
                u.setEmail("admin@recetas.com");
                u.setPassword(encoder.encode("q1w2e3###"));
                repo.save(u);
            }
        };
    }

}

