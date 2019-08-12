package com.tradeservice.project.repository.product;

import static com.tradeservice.project.util.TestConstants.PRODUCT_1;
import static com.tradeservice.project.util.TestConstants.PRODUCT_LIST;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import com.tradeservice.project.entity.Product;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  @AfterEach
  void tearDown() {
    productRepository.deleteAll();
  }

  @Test
  void testSave() {
    Product newProduct = populateProduct();
    Assertions.assertNotNull(productRepository.findAll()
        .stream().filter(p -> p.equals(newProduct)).findAny().orElse(null));
  }

  @Test
  void testFindAllByIds() {
    List<Product> products = productRepository.saveAll(PRODUCT_LIST);
    List<Product> productsFound = productRepository.findAllById(products
        .stream().map(Product::getProductId).collect(toList()));
    Assertions.assertEquals(productsFound, products);
  }

  @Test
  void testDelete() {
    Product newProduct = populateProduct();
    productRepository.delete(newProduct);
    Assertions.assertEquals(0, productRepository.findAll().size());
  }

  @Test
  void testUpdate() {
    Product newProduct = populateProduct();
    String newName = "new Name";
    newProduct.setName(newName);
    productRepository.save(newProduct);
    assertThat(newProduct).isNotEqualTo(PRODUCT_1);
    Assertions.assertEquals(productRepository.findById(newProduct.getProductId()).get().getName(),
        newName);
  }

  private Product populateProduct() {
    return productRepository.save(PRODUCT_1);
  }
}
