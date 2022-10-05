package com.rafael.paymentspringjpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rafael.paymentspringjpa.entities.pk.OrderItemPK;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity @Table(name = "tb_order_item")
@NoArgsConstructor
public class OrderItem implements Serializable {

    @EmbeddedId //por se tratar de um id composto preciso colocar o embeddedId
    private OrderItemPK id = new OrderItemPK();//sempre instanciar qdo for id composto

    @JsonIgnore
    private Integer quantity;
    private Double price;

    /*@ManyToOne
    @JsonBackReference
    private Product product = new Product();

    @ManyToOne
    @JsonBackReference
    private Order order = new Order();*/

    //meu OrderItem tbm tem um Order e um Produto
    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        super();
        id.setOrder(order); //atribuindo o Order no OrderItem
        id.setProduct(product); //atribuindo o Product no OrderItem
        this.quantity = quantity;
        this.price = price;
    }



    public void setOrder(Order order) { //to informando um 'order'. o metodo vai no meu id (OrderItemPK) e joga o 'order' la dentro
        id.setOrder(order);
    }

    public Order getOrder() {
        return id.getOrder();
    }

    public void setProduct(Product product) { //to informando um 'product'. o metodo vai no meu id (OrderItemPK) e joga o 'product' la dentro
        id.setProduct(product);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSubTotal() {
        return price * quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
