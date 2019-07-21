package com.tradeservice.TestProject.repository;

import com.tradeservice.TestProject.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

  @Query("select o from Order1 o where o.name = :name")
  Order findByName(@Param("name") String name);
}
