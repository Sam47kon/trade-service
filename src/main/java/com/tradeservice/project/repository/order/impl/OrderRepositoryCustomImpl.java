package com.tradeservice.project.repository.order.impl;

import com.tradeservice.project.entity.Order;
import com.tradeservice.project.repository.order.OrderRepositoryBasic;
import com.tradeservice.project.repository.order.OrderRepositoryCustom;
import javax.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {
    private final OrderRepositoryBasic orderRepositoryBasic;
    private final EntityManager        entityManager;

    public OrderRepositoryCustomImpl(OrderRepositoryBasic orderRepositoryBasic, EntityManager entityManager1) {
        this.orderRepositoryBasic = orderRepositoryBasic;
        this.entityManager = entityManager1;
    }

    @Override
    @Transactional
    public Order saveAndRefresh(Order order) {
        orderRepositoryBasic.save(order);
        entityManager.refresh(order);
        return order;
    }
}
