package com.rafael.paymentspringjpa.entities;

import com.rafael.paymentspringjpa.entities.pk.OrdemItemPK;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity @Table(name = "tb_order_item")
@NoArgsConstructor
public class OrdemItem implements Serializable {

    @EmbeddedId //por se tratar de um id composto preciso colocar o embeddedId
    private OrdemItemPK id;

    private Integer quantity;
    private Double price;

    //meu OrderItem tbm tem um Order e um Produto
    public OrdemItem(Order order, Product product, Integer quantity, Double price) {
        id.setOrder(order); //atribuindo o Order no OrderItem
        id.setProduct(product); //atribuido o Product no OrderItem
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdemItem ordemItem = (OrdemItem) o;

        return Objects.equals(id, ordemItem.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
