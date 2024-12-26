package com.bookbouqet;

import com.bookbouqet.entity.Role;
import com.bookbouqet.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
public class BookBouquetApplication {


    public static void main(String[] args) {
        SpringApplication.run(BookBouquetApplication.class, args);
    }


    @Bean
    public CommandLineRunner runner(RoleRepository roleRepository) {
        return args -> {
            if(roleRepository.count() == 0) {
                roleRepository.save(Role.builder().name("USER").build());
            }

        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
     return config.getAuthenticationManager();
    }
}
