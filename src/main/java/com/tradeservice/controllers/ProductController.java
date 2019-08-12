package com.tradeservice.controllers;

import com.tradeservice.ecxeptions.ProductNotFoundException;
import com.tradeservice.entity.Product;
import com.tradeservice.service.ProductService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

  private ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }


  /**
   * 6) - добавление нового товара
   *
   * @param newProductRequest добавляемый товар
   * @return HttpStatus.CREATED if ok, and added Product POST http://localhost:8080/products/ JSON
   */
  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody Product newProductRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(productService.add(newProductRequest));
  }

  /**
   * 7) - изменение существующего товара, поиск в базе по id
   *
   * @param newProductRequest товар
   * @param id id изменяемого
   * @return HttpStatus.ACCEPTED, сам товар, измененный PUT http://localhost:8080/products/{id} JSON
   */
  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@RequestBody Product newProductRequest,
      @PathVariable Long id) {
    return ResponseEntity.accepted().body(productService.update(newProductRequest, id));
  }

  /**
   * 8)	- удаление товара
   *
   * @param id - id товара
   * @return HttpStatus.ACCEPTED DELETE http://localhost:8080/products/{id}
   */
  @DeleteMapping("/{id}")
  public ResponseEntity deleteProduct(@PathVariable Long id) {
    productService.delete(id);
    return ResponseEntity.accepted().build();
  }

  /**
   * 9) - получение всех товаров
   *
   * @return HttpStatus.OK, Collection все товары GET http://localhost:8080/products
   */
  @GetMapping
  public ResponseEntity<Collection<Product>> getAllProducts() {
    return ResponseEntity.ok(productService.getAll());
  }

  /**
   * 10)	- получение определенного товара по id
   *
   * @param id - id товара
   * @return HttpStatus.OK, товар, если найден GET http://localhost:8080/products/{id}
   */
  @GetMapping("/{id}")
  public ResponseEntity<Product> getProduct(@PathVariable Long id) {
    return ResponseEntity
        .ok(productService.getById(id).orElseThrow(() -> new ProductNotFoundException(id)));
  }
}
