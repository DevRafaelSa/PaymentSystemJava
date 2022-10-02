package com.rafael.paymentspringjpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor @AllArgsConstructor
@Data @Entity @Table(name = "tb_category")
public class Category implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //Eu poderia usar o @Transient. Ele impede que o Jpa tente interpretar o relacionamento
    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    public Set<Product> getProducts() {
        return products;
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
