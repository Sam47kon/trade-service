package com.tradeservice.db;

import com.tradeservice.entities.Goods;
import com.tradeservice.entities.Order;
import com.tradeservice.entities.OrderLine;
import com.tradeservice.services.GoodsService;
import com.tradeservice.services.OrderService;
import java.util.Date;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class LoadDatabase {

  @Bean
  CommandLineRunner initDataBase(GoodsService goodsService, OrderService orderService) {
    Goods newGoods1 = new Goods("Товар 1", 59.0);
    Goods newGoods2 = new Goods("Товар 2", 999.0);
    Order newOrder1 = new Order("Клиент 1", new Date(), "Адрес 1");
    Order newOrder2 = new Order("Клиент 1", new Date(), "Адрес 1");

    Set<OrderLine> orderLineSet1 =
        Set.of(new OrderLine(newGoods1, 11), new OrderLine(newGoods2, 12));
    newOrder1.setOrderItems(orderLineSet1);

    Set<OrderLine> orderLineSet2 =
        Set.of(new OrderLine(newGoods1, 22), new OrderLine(newGoods2, 23));
    newOrder2.setOrderItems(orderLineSet2);

    return args -> {
      log.info("Add new goods1: " + goodsService.add(newGoods1));
      log.info("Add new goods2: " + goodsService.add(newGoods2));
      log.info("Add new order1: " + orderService.add(newOrder1));
      log.info("Add new order2: " + orderService.add(newOrder2));
    };
  }
}
