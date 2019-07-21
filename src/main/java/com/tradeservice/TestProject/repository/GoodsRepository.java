package com.tradeservice.TestProject.repository;

import com.tradeservice.TestProject.entities.Goods;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

  @Query("select g from Goods g where g.name = :name")
  Goods findByName(@Param("name") String name);

  List<Goods> findByPriceLike(Double price);
}
