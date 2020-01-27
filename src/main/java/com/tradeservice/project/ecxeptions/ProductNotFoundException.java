package com.tradeservice.project.ecxeptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Такого товара нет. id:" + id);
    }
}
