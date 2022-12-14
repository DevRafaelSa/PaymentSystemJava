package com.rafael.paymentspringjpa.controllers;

import com.rafael.paymentspringjpa.entities.User;
import com.rafael.paymentspringjpa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//para falar q a classe é um recurso web que é implementado por um controlador Rest, a gente vai
// colocar uma anotação em cima do nome da classe;
@RestController
@RequestMapping(value = "/users")//nome pro recurso - endpoint
public class UserController {

    //para a classe UserController faca a injecao automatica do UserService por meio do AutoWired, é necessário
    //registrar a classe UserService como Componente do Spring
    @Autowired
    private UserService service;


    //metodo que sera um endpoint para acessar os usuarios
    //ResponseEntity(generics) é um tipo de retorno que é específico do Spring para retornar respostas de requisições Web
    //para indicar q esse metodo responde a uma requisicao do tipo Get do http, preciso colocar uma anotacao
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findall();
        //retornando um responseentity, o "ok" para retornar uma resposta com sucesso, ".body" pra
        // retornar o corpo da resposta o usuario u q acabamos de instanciar
        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value = "/{id}") //esse formato eh para indicar que minha requisicao vai aceitar um ID dentro da url
    //o @pathvariable foi para o spring aceitar o id e considera-lo como um parametro que vai chegar na url
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj) { //o @RequestBody é para dizer que o objeto vai chegar no modo Json e esse Json vai ser descerializado para um obj User do Java
        obj = service.insert(obj);
        //metodo para gerar um URI q eh necessario para gerar novos recursos e retornar 201 no postman... forma adequada de inserir um recuro no BD
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj); //para retornar um 201

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { //para q o Long id sea reconhecido como uma variavel da minha url usei o PathVariable
        service.delete(id);
        return ResponseEntity.noContent().build(); //o noContent retorna uma resposta vazia e o codigo http de uma resposta vazia eh o 204
    }

    @PutMapping(value = "/{id}") //recebo um id e um objeto contendo os dados para atualizar
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}
