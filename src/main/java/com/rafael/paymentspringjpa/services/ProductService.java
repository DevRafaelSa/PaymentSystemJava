package com.rafael.paymentspringjpa.services;

/* Vantagens da camada de serviço
porque a camada de servico? pq atraves dela separamos melhor as responsabilidades, regras do negocio do controlador, orquestracao de repositorios
p.e.: na hora de fazer um pedido eu posso antes querer verificar o estoque, salvar os itens para depois salvar os itens
se eu colocasse td no controlador apenas chamando as operacoes de salvamento do repository, o controlador ia ficar
mto carregado de regras de negocios

Desvantagens:

vao ter mtas operacoes em que a camade de servico vai simplesmente repassar para o repository a chamada de alguma coisa.

*/

import com.rafael.paymentspringjpa.entities.Product;
import com.rafael.paymentspringjpa.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    //para fazer a injecao de dependencia automaticamente
    @Autowired
    private ProductRepository repository;

    public List<Product> findall() {
        return repository.findAll();
    }

    //implementacao do metodo que busca usuarios pelo ID
    public Product findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        return obj.get();
    }
}
