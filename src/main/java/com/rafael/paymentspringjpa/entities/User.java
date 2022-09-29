package com.rafael.paymentspringjpa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


//SERIALIZABLE - vc define ela nos seus objetos quando vc quer q esses objetos possam ser
// transformados em cadeias de bytes. Para que? Para que o trafegue na rede, gravado em arquivos e assim por diante
//as anotacoes do JPA servem para intruir o JPA como q ele vai converter os objetos para o modelo relacional
//Data lomboc -
@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //estrategia de auto-incremento para o ID
    private Long id; //long para representar Id

    private String name;
    private String email;
    private String phone;
    private String password;

    @OneToMany
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

}
