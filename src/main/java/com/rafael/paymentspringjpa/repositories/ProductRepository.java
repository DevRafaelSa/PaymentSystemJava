package com.rafael.paymentspringjpa.repositories;

import com.rafael.paymentspringjpa.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//se vc criou a interface, entao tera q criar a implementacao da interface?
//neste caso nao, porque o springDataJpa ja tem uma implementacao padrao para essa interface.
//se vc definir a sua entidade e o tipo de sua entidade vc ja tem uma implementacao padrao
//para o q vc definiu ali.
//classe usada para salvar os dados
//como a classeUserRepository esta herdando do JpaRepository, q ja esta registrada como componente do Spring, logo
//a anotacao Repository eh opcional
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
