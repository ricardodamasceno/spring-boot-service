package com.spring.domain.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException (String message){
        super(message);
    }

}
