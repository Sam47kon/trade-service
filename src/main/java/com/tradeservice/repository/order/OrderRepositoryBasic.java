package com.tradeservice.repository.order;

import com.tradeservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryBasic extends JpaRepository<Order, Long>
{
}
