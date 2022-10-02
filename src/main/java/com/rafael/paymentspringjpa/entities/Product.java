package com.rafael.paymentspringjpa.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data @Entity
@Table(name = "tb_products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    //o Set representa um conjunto. Ele serve para garantir que um produto nao tenha mais de uma ocorrencia para a mesma categoria
    //porque instanciar? para garantir que a colecao nao comece valendo nulo. Ela tem q comecar vazia, porem instaniada.
    //porque usamos o hashSet e nao o Set? o Set eh uma interface, de modo q nao pode ser instanciado. Tenho q usar, portanto, uma
    //classe correspondente a essa interface
    //Eu poderia usar o @Transient. Ele impede que o Jpa tente interpretar o relacionamento
    @ManyToMany
    private Set<Category> categories = new HashSet<>();

    //como ja esto instanciando a colecao acima eu n preciso coloca-la dentro do construtor
    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }
}
