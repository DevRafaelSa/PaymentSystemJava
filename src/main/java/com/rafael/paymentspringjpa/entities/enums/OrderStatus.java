package com.rafael.paymentspringjpa.entities.enums;


public enum OrderStatus {

    WAITTING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private int code;

    OrderStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    //estatico pq esse metodo vai funcionar sem precisar instanciar
    //eu vou pasar um codigo e o metodo vai me retornar o OrderStatus correspondente aquele codigo
    public static OrderStatus valueOf(int code) {
        //percorrendo os valores do OrderStatus
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        //caso ele n encontre um valor para o cod q eu informar significa dizer que informei um cod nulo
        throw new IllegalArgumentException("Ivalid OrderStatus code");
    }


}
