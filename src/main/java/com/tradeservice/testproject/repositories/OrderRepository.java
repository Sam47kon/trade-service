package com.tradeservice.testproject.repositories;

import com.tradeservice.testproject.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

  Order findByClient(String client);
}
