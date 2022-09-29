package com.rafael.paymentspringjpa.config;

import com.rafael.paymentspringjpa.entities.User;
import com.rafael.paymentspringjpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

//para falar q eh uma classe de configuracao + indicando qual perfil ele vai roda essa configuracao
//essa classe depende do Repoitoty
@Configuration @Profile("test")
public class TestConfig implements CommandLineRunner {
    //o CommandLineRunner -> é para reforcar o programa a executar isso sempre que o programa for iniciado

    //para que o spring resolver a dependencia entre classes e assossiar
    // uma instancia de UserRepository no TestConfig
    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        //tudo que eu colocar sera executado quando a aplicacao for iniciada
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        //salvando no banco de dados
        userRepository.saveAll(Arrays.asList(u1, u2));
    }
}