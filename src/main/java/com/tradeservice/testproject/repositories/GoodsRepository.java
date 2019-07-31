package com.tradeservice.testproject.repositories;

import com.tradeservice.testproject.entities.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

  Goods findByName(String name);
}
