package com.rafael.paymentspringjpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
//Lomboc - ja cria os construtores, gets e setters, hashcode e equals
@Data
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "tb_user")
public class User implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //estrategia de auto-incremento para o ID
    private Long id; //long para representar Id
    private String name;
    private String email;
    private String phone;
    private String password;

    @JsonIgnore //para evitar a serializacvao de mao dupla (q cria um loop infinito, pois um chama o outro indefinidamente)
    @OneToMany(mappedBy = "client")//colocar o nome do atributo la do outro lado da assossiação
    private List<Order> orders = new ArrayList<>();

    public User(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}
