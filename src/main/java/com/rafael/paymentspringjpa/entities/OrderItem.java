package com.rafael.paymentspringjpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rafael.paymentspringjpa.entities.pk.OrderItemPK;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Table(name = "tb_order_item")
@NoArgsConstructor
public class OrderItem implements Serializable {

    @EmbeddedId //por se tratar de um id composto preciso colocar o embeddedId
    private OrderItemPK id = new OrderItemPK();//sempre instanciar qdo for id composto

    private Integer quantity;
    private Double price;

    //meu OrderItem tbm tem um Order e um Produto
    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        super();
        id.setOrder(order); //atribuindo o Order no OrderItem
        id.setProduct(product); //atribuindo o Product no OrderItem
        this.quantity = quantity;
        this.price = price;
    }

    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubTotal() {
        return price * quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderItem other = (OrderItem) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
