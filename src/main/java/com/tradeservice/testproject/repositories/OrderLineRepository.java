package com.tradeservice.testproject.repositories;

import com.tradeservice.testproject.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

  OrderLine findByCount(Long count);
}
