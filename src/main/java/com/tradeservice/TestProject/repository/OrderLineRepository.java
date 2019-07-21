package com.tradeservice.TestProject.repository;

import com.tradeservice.TestProject.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

  @Query("select g from Goods g where g.name = ?")
  OrderLine findByName(String name);
}
