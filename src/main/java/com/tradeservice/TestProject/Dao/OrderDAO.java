package com.tradeservice.TestProject.Dao;

import com.tradeservice.TestProject.Entities.Order1;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAO { // TODO

  List<Order1> orders;

  public List<Order1> findAll() {
    return orders;
  }
}
