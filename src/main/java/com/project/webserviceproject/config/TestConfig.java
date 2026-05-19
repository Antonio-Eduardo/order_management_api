package com.project.webserviceproject.config;

import com.project.webserviceproject.entities.User;
import com.project.webserviceproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Markin", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Vini Junior", "alex@gmail.com", "977777777", "123456");
        User u3 = new User(null, "Virginia", "instagramdasilva@gmail.com", "97123137", "123456");

        userRepository.saveAll(Arrays.asList(u1,u2,u3));
    }
}
