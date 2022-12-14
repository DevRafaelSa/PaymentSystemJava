package com.rafael.paymentspringjpa.controllers;

import com.rafael.paymentspringjpa.entities.Order;
import com.rafael.paymentspringjpa.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//para falar q a classe é um recurso web que é implementado por um controlador Rest, a gente vai
// colocar uma anotação em cima do nome da classe;
@RestController
@RequestMapping(value = "/orders")//nome pro recurso - endpoint
public class OrderController {

    //para a classe OrderController faca a injecao automatica do OrderService por meio do AutoWired, é necessário
    //registrar a classe OrderService como Componente do Spring
    @Autowired
    private OrderService service;


    //metodo que sera um endpoint para acessar os usuarios
    //ResponseEntity(generics) é um tipo de retorno que é específico do Spring para retornar respostas de requisições Web
    //para indicar q esse metodo responde a uma requisicao do tipo Get do http, preciso colocar uma anotacao
    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> list = service.findall();
        //retornando um responseentity, o "ok" para retornar uma resposta com sucesso, ".body" pra
        // retornar o corpo da resposta o usuario u q acabamos de instanciar
        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value = "/{id}") //esse formato eh para indicar que minha requisicao vai aceitar um ID dentro da url
    //o @pathvariable foi para o spring aceitar o id e considera-lo como um parametro que vai chegar na url
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Order obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

}
