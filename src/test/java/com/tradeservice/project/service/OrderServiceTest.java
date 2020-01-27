package com.tradeservice.project.service;

import com.tradeservice.project.ecxeptions.OrderNotFoundException;
import com.tradeservice.project.entity.Order;
import com.tradeservice.project.entity.OrderItem;
import com.tradeservice.project.repository.order.OrderRepository;
import com.tradeservice.project.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.tradeservice.project.util.TestConstants.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    private OrderService orderService;
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderServiceImpl(orderRepository);
    }

    @Test
    void testAdd() {
        Assertions.assertDoesNotThrow(() -> orderService.add(ORDER));
        verify(orderRepository).saveAndRefresh(ORDER);
        when(orderRepository.findById(ORDER.getOrderId())).thenReturn(Optional.of(ORDER));

        Assertions.assertEquals(Optional.of(ORDER), orderService.getById(ORDER.getOrderId()));
        verify(orderRepository).findById(ORDER.getOrderId());
        verifyNoMoreInteractions(orderRepository);
    }


    @Test
    void testUpdate() {
        when(orderRepository.findById(ORDER.getOrderId())).thenReturn(Optional.of(ORDER_TO_UPDATE));
        Assertions.assertDoesNotThrow(() -> orderService.update(ORDER_TO_UPDATE, ORDER.getOrderId()));
        verify(orderRepository).saveAndFlush(ORDER_TO_UPDATE);
        verifyNoMoreInteractions(orderRepository);
    }

    @Test
    void testDelete() {
        Assertions.assertThrows(OrderNotFoundException.class,
                () -> orderService.delete(ORDER.getOrderId()));

        when(orderRepository.findById(ORDER.getOrderId())).thenReturn(Optional.of(ORDER));
        Assertions.assertDoesNotThrow(() -> orderService.delete(ORDER.getOrderId()));

        verify(orderRepository).delete(ORDER);
        verify(orderRepository, times(2)).findById(ORDER.getOrderId());
        verifyNoMoreInteractions(orderRepository);
    }

    @Test
    void testGetAll() {
        Set<OrderItem> orderItems = Set.of(new OrderItem(PRODUCT_1, 115),
                new OrderItem(PRODUCT_2, 116));
        Order order2 = new Order(2L, "Sam", LocalDateTime.now(), "London", orderItems);

        when(orderRepository.findAll()).thenReturn(List.of(ORDER, order2));
        Assertions.assertTrue(orderService.getAll().containsAll(List.of(ORDER, order2)));
        verify(orderRepository).findAll();
        verifyNoMoreInteractions(orderRepository);
    }

    @Test
    void testGetById() {
        when(orderRepository.findById(ORDER.getOrderId())).thenReturn(Optional.of(ORDER));
        Assertions.assertEquals(Optional.of(ORDER), orderService.getById(ORDER.getOrderId()));

        when(orderRepository.findById(NOT_EXIST_ID)).thenThrow(OrderNotFoundException.class);
        Assertions.assertThrows(OrderNotFoundException.class, () -> orderService.getById(NOT_EXIST_ID));

        verify(orderRepository).findById(ORDER.getOrderId());
        verify(orderRepository).findById(NOT_EXIST_ID);
        verifyNoMoreInteractions(orderRepository);
    }
}
