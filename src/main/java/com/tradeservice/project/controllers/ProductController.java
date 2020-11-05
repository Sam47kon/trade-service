package com.tradeservice.project.controllers;

import com.tradeservice.project.ecxeptions.ProductNotFoundException;
import com.tradeservice.project.entity.Product;
import com.tradeservice.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

	private final ProductService productService;

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
	 * @param id                id изменяемого
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
	public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
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
