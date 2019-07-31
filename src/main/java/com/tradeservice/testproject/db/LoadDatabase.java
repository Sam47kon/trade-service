package com.tradeservice.testproject.db;

import com.tradeservice.testproject.entities.Goods;
import com.tradeservice.testproject.services.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class LoadDatabase {

  @Bean
  CommandLineRunner initDataBase(GoodsService goodsService) {
    return args -> {
      log.info("Загрузка: " + goodsService.add(new Goods(1L, "Новый товар 1", 54.0)));
    };
  }
}
