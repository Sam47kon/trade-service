package com.tradeservice.repository.order;

import com.tradeservice.entity.Order;

public interface OrderRepositoryCustom {
    Order saveAndRefresh(Order order);
}
