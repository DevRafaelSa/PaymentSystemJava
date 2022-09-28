package com.rafael.paymentspringjpa.controllers;

import com.rafael.paymentspringjpa.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//para falar q a classe é um recurso web que é implementado por um controlador Rest, a gente vai
// colocar uma anotação em cima do nome da classe;
@RestController
@RequestMapping(value = "/users")//nome pro recurso
public class UserController {

    //metodo que sera um endpoint para acessar os usuarios
    //ResponseEntity(generics) é um tipo de retorno que é específico do Spring para retornar respostas de requisições Web
    //para indicar q esse metodo responde a uma requisicao do tipo Get do http, preciso colocar uma anotacao
    @GetMapping
    public ResponseEntity<User> findAll() {
        User u = new User(1L, "Maria", "maria@gmail.com", "99999999", "12345");
        //retornando um responseentity, o "ok" para retornar uma resposta com sucesso, ".body" pra
        // retornar o corpo da resposta o usuario u q acabamos de instanciar
        return ResponseEntity.ok().body(u);

    }
}
