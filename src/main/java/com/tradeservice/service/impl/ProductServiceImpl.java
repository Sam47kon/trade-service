package com.tradeservice.service.impl;

import com.tradeservice.ecxeptions.ProductNotFoundException;
import com.tradeservice.entity.Product;
import com.tradeservice.repository.product.ProductRepository;
import com.tradeservice.service.ProductService;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }


  @Override
  public Product add(Product addProductRq) {
    return productRepository.save(addProductRq);
  }

  @Override
  public Product update(Product newProduct, Long id) {
    return productRepository.findById(id)
        .map(goods -> {
          if (newProduct.getName() != null) {
            goods.setName(newProduct.getName());
          }
          if (newProduct.getPrice() != null) {
            goods.setPrice(newProduct.getPrice());
          }
          return productRepository.saveAndFlush(goods);
        }).orElseThrow(() -> new ProductNotFoundException(id));
  }

  @Override
  public void delete(Long id) {
    productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    productRepository.deleteById(id);
  }

  @Override
  public Collection<Product> getAll() {
    return productRepository.findAll();
  }

  @Override
  public Optional<Product> getById(Long id) {
    return productRepository.findById(id);
  }
}
