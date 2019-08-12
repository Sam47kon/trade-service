package com.tradeservice.project.repository.order;

import com.tradeservice.project.entity.Order;

public interface OrderRepositoryCustom {
    Order saveAndRefresh(Order order);
}
