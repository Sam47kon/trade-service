package com.tradeservice.project.repository.order;

import com.tradeservice.project.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryBasic extends JpaRepository<Order, Long> {
}
