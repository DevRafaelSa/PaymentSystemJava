package com.rafael.paymentspringjpa.repositories;

import com.rafael.paymentspringjpa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

//se vc criou a interface, entao tera q criar a implementacao da interface?
//neste caso nao, porque o springDataJpa ja tem uma implementacao padrao para essa interface.
//se vc definir a sua entidade e o tipo de sua entidade vc ja tem uma implementacao padrao
//para o q vc definiu ali.
//classe usada para salvar os dados
public interface UserRepository extends JpaRepository<User, Long> {

}
