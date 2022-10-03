package com.rafael.paymentspringjpa.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rafael.paymentspringjpa.entities.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity @Table(name = "tb_order")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT") //estabelecendo o formato da data
    private Instant moment;

    //macete para controlar o codigo de cada OrderStatus... esse tratamenro do Integer é apenas na classe
    private Integer orderStatus;

    @ManyToOne
    @JoinColumn(name = "client_id") //nome da chave estrangeira
    private User client;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
        super();
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus); //recebi inteiro e transformei num OderStatus
        this.client = client;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getCode();
        }

    }

    public Set<OrderItem> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
