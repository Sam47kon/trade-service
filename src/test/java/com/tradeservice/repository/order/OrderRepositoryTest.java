package com.tradeservice.repository.order;

import static com.tradeservice.util.TestConstants.CLIENT_NAME;
import static com.tradeservice.util.TestConstants.PRODUCT_1;
import static com.tradeservice.util.TestConstants.PRODUCT_2;

import com.tradeservice.entity.Order;
import com.tradeservice.entity.OrderItem;
import com.tradeservice.entity.Product;
import com.tradeservice.repository.product.ProductRepository;
import java.time.LocalDateTime;
import java.util.Set;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ProductRepository productRepository;

  @Before
  public void before() {
    populateOrderAndItsProducts();
  }

  @After
  public void tearDown() {
    productRepository.deleteAll();
    orderRepository.deleteAll();
  }

  @Test
  public void testSave() {
    orderRepository.findAll();
    Assert.assertEquals(orderRepository.findAll().get(0).getClientName(), CLIENT_NAME);
  }

  @Test
  public void testRemove() {
    Order order = orderRepository.findAll().get(0);
    Assert.assertEquals(1, orderRepository.findAll().size());
    orderRepository.delete(order);
    Assert.assertEquals(0, orderRepository.findAll().size());
  }

  @Test
  public void testUpdate() {
    Order order = orderRepository.findAll().get(0);
    String expectedClientName = "Google Inc.";
    order.setClientName(expectedClientName);
    orderRepository.saveAndFlush(order);
    Assert.assertEquals(expectedClientName, orderRepository.findAll().get(0).getClientName());
  }


  private void populateOrderAndItsProducts() {
    Product product1 = productRepository.save(PRODUCT_1);
    Product product2 = productRepository.save(PRODUCT_2);
    Set<OrderItem> orderItems = Set.of(new OrderItem(product1, 115), new OrderItem(product2, 116));
    Order newOrder = new Order(CLIENT_NAME, LocalDateTime.now(), "Los Angeles, California 90002",
        orderItems);
    orderRepository.saveAndRefresh(newOrder);
  }
}
