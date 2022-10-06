package com.rafael.paymentspringjpa.services;

/* Vantagens da camada de serviço
porque a camada de servico? pq atraves dela separamos melhor as responsabilidades, regras do negocio do controlador, orquestracao de repositorios
p.e.: na hora de fazer um pedido eu posso antes querer verificar o estoque, salvar os itens para depois salvar os itens
se eu colocasse td no controlador apenas chamando as operacoes de salvamento do repository, o controlador ia ficar
mto carregado de regras de negocios

Desvantagens:

vao ter mtas operacoes em que a camade de servico vai simplesmente repassar para o repository a chamada de alguma coisa.

*/

import com.rafael.paymentspringjpa.entities.User;
import com.rafael.paymentspringjpa.repositories.UserRepository;
import com.rafael.paymentspringjpa.services.exceptions.DataBaseException;
import com.rafael.paymentspringjpa.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    //para fazer a injecao de dependencia automaticamente
    @Autowired
    private UserRepository repository;

    public List<User> findall() {
        return repository.findAll();
    }

    //implementacao do metodo que busca usuarios pelo ID
    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    //retorna um usuario q foi inserido
    public User insert(User obj) {
        return repository.save(obj); // o save por padrao ja retorna o objeto salvo
    }

    //n retorna nada, so apaga
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    //retorna um usuario que foi editado/alterado
    public User update(Long id, User obj) {
        try {
            User entity = repository.getReferenceById(id); //o getReference ele vai instanciar um usuario mas ainda sem ir no BD. ele so deixa o objeto monitorado pelo JPA para trabalhar com ele e so depois trabalhar com o BD
            //...diferentemente do findById q vai no BD e traz o obj pra gente
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }

    }

    //metodo em q vou ter q atualizar os dados do entity com o que chegou do obj
    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}
