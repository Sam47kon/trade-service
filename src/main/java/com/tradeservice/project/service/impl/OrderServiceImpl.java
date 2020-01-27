package com.tradeservice.project.service.impl;

import com.tradeservice.project.ecxeptions.OrderNotFoundException;
import com.tradeservice.project.entity.Order;
import com.tradeservice.project.repository.order.OrderRepository;
import com.tradeservice.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public Order add(Order newOrderRequest) {
        newOrderRequest.setOrderId(null);
        return orderRepository.saveAndRefresh(newOrderRequest);
    }

    @Override
    public Order update(Order newOrderRequest, Long id) {
        orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        newOrderRequest.setOrderId(id);
        return orderRepository.saveAndFlush(newOrderRequest);
    }

    @Override
    public void delete(Long id) {
        orderRepository.delete(orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id)));
    }

    @Override
    public Collection<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getById(Long id) {
        return orderRepository.findById(id);
    }
}
