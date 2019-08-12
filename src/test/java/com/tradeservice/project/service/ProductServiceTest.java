package com.tradeservice.project.service;

import static com.tradeservice.project.util.TestConstants.NOT_EXIST_ID;
import static com.tradeservice.project.util.TestConstants.PRODUCT_1;
import static com.tradeservice.project.util.TestConstants.PRODUCT_1_TO_UPDATE;
import static com.tradeservice.project.util.TestConstants.PRODUCT_LIST;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.tradeservice.project.ecxeptions.ProductNotFoundException;
import com.tradeservice.project.repository.product.ProductRepository;
import com.tradeservice.project.service.impl.ProductServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  private ProductService productService;
  private ProductRepository productRepository;

  @BeforeEach
  void setUp() {
    productRepository = mock(ProductRepository.class);
    productService = new ProductServiceImpl(productRepository);
  }


  @Test
  void testAdd() {
    Assertions.assertDoesNotThrow(() -> productService.add(PRODUCT_1));
    when(productRepository.findById(PRODUCT_1.getProductId())).thenReturn(Optional.of(PRODUCT_1));
    verify(productRepository).save(PRODUCT_1);

    Assertions.assertEquals(Optional.of(PRODUCT_1),
        productService.getById(PRODUCT_1.getProductId()));
    verifyNoMoreInteractions(productRepository);
  }

  @Test
  void testUpdate() {
    when(productRepository.findById(PRODUCT_1.getProductId())).thenReturn(Optional.of(PRODUCT_1));
    when(productRepository.saveAndFlush(PRODUCT_1_TO_UPDATE)).thenReturn(PRODUCT_1_TO_UPDATE);
    Assertions.assertEquals(PRODUCT_1_TO_UPDATE,
        productService.update(PRODUCT_1_TO_UPDATE, PRODUCT_1.getProductId()));

    verify(productRepository).saveAndFlush(PRODUCT_1_TO_UPDATE);
    verify(productRepository).findById(PRODUCT_1.getProductId());
    verifyNoMoreInteractions(productRepository);
  }

  @Test
  void testDelete() {
    Assertions.assertThrows(ProductNotFoundException.class,
        () -> productService.delete(PRODUCT_1.getProductId()));

    when(productRepository.findById(PRODUCT_1.getProductId())).thenReturn(Optional.of(PRODUCT_1));
    Assertions.assertDoesNotThrow(() -> productService.delete(PRODUCT_1.getProductId()));

    verify(productRepository, times(2)).findById(PRODUCT_1.getProductId());
    verify(productRepository).deleteById(PRODUCT_1.getProductId());

    verifyNoMoreInteractions(productRepository);
  }

  @Test
  void testGetAll() {
    when(productRepository.findAll()).thenReturn(PRODUCT_LIST);
    Assertions.assertTrue(productService.getAll().containsAll(PRODUCT_LIST));
    verify(productRepository).findAll();
    verifyNoMoreInteractions(productRepository);
  }

  @Test
  void testGetById() {
    when(productRepository.findById(PRODUCT_1.getProductId())).thenReturn(Optional.of(PRODUCT_1));
    Assertions.assertEquals(Optional.of(PRODUCT_1),
        productService.getById(PRODUCT_1.getProductId()));

    when(productRepository.findById(NOT_EXIST_ID)).thenThrow(ProductNotFoundException.class);
    Assertions.assertThrows(ProductNotFoundException.class,
        () -> productService.getById(NOT_EXIST_ID));

    verify(productRepository).findById(PRODUCT_1.getProductId());
    verify(productRepository).findById(NOT_EXIST_ID);
    verifyNoMoreInteractions(productRepository);
  }
}
