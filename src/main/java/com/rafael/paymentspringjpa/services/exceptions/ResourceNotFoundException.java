package com.rafael.paymentspringjpa.services.exceptions;

//subClasse do RunTimeException, que eh a excecao em que o compilador não nos obriga a tratar
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {
        super("Resource not found. Id " + id);
    }
}
