package com.rafael.paymentspringjpa.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data @Entity @NoArgsConstructor
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
    @JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id")) //chaves estrangeiras
    private Set<Category> categories = new HashSet<>();

    //lembrando que o Set eh para nao admitir repeticoes do mesmo Item
    @OneToMany(mappedBy = "id.product", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<OrderItem> items = new HashSet<>();

    //como ja esto instanciando a colecao acima eu n preciso coloca-la dentro do construtor
    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    @JsonIgnore
    public Set<Order> getOrders() {
        Set<Order> set = new HashSet<>();
        for (OrderItem x : items) { //estou percorrendo minha colecao items
            set.add(x.getOrder());
        }
        return set;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
