package com.tradeservice.TestProject.Dao;

import com.tradeservice.TestProject.Entities.OrderLine;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class OrderLineDAO { // TODO

  List<OrderLine> orderLineList;

  public List<OrderLine> findAll() {
    return orderLineList;
  }
}
