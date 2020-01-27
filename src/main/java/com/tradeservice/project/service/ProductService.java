package com.tradeservice.project.service;

import com.tradeservice.project.entity.Product;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {

    Product add(Product product);

    Product update(Product newProduct, Long id);

    void delete(Long id);

    Collection<Product> getAll();

    Optional<Product> getById(Long id);
}
