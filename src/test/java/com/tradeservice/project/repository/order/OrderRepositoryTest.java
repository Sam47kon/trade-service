package com.tradeservice.project.repository.order;

import com.tradeservice.project.entity.Order;
import com.tradeservice.project.entity.OrderItem;
import com.tradeservice.project.entity.Product;
import com.tradeservice.project.repository.product.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Set;

import static com.tradeservice.project.util.TestConstants.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void before() {
        populateOrderAndItsProducts();
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    void testSave() {
        orderRepository.findAll();
        Assertions.assertEquals(orderRepository.findAll().get(0).getClientName(), CLIENT_NAME);
    }

    @Test
    void testRemove() {
        Order order = orderRepository.findAll().get(0);
        Assertions.assertEquals(1, orderRepository.findAll().size());
        orderRepository.delete(order);
        Assertions.assertEquals(0, orderRepository.findAll().size());
    }

    @Test
    void testUpdate() {
        Order order = orderRepository.findAll().get(0);
        String expectedClientName = "Google Inc.";
        order.setClientName(expectedClientName);
        orderRepository.saveAndFlush(order);
        Assertions.assertEquals(expectedClientName, orderRepository.findAll().get(0).getClientName());
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
