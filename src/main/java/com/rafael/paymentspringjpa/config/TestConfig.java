package com.rafael.paymentspringjpa.config;

import com.rafael.paymentspringjpa.entities.Category;
import com.rafael.paymentspringjpa.entities.Order;
import com.rafael.paymentspringjpa.entities.User;
import com.rafael.paymentspringjpa.entities.enums.OrderStatus;
import com.rafael.paymentspringjpa.repositories.CategoryRepository;
import com.rafael.paymentspringjpa.repositories.OrderRepository;
import com.rafael.paymentspringjpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
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

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        //tudo que eu colocar sera executado quando a aplicacao for iniciada
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.DELIVERED, u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITTING_PAYMENT, u1);
        //salvando no banco de dados
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
    }
}
