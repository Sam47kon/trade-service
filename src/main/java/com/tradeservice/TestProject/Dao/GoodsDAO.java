package com.tradeservice.TestProject.Dao;

import com.tradeservice.TestProject.Entities.Goods;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsDAO extends CrudRepository<Goods, Long> { // TODO

//  List<Goods> goods;
//
//  public List<Goods> findAll() {
//    return goods;
//  }
}
