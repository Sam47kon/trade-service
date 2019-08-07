package com.tradeservice.repositories;

import com.tradeservice.entities.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
}
