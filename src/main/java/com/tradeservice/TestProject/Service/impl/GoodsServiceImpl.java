package com.tradeservice.TestProject.Service.impl;

import com.tradeservice.TestProject.Service.GoodsService;
import com.tradeservice.TestProject.entities.Goods;
import com.tradeservice.TestProject.repository.GoodsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class GoodsServiceImpl implements GoodsService {

  @Autowired
  private GoodsRepository goodsRepository;

  @Override
  public Goods addGoods(Goods goods) {
    return goodsRepository.save(goods);
  }

  @Override
  public void delete(Long id) {
    goodsRepository.deleteById(id);
  }

  @Override
  public Goods getByName(String name) {
    return goodsRepository.findByName(name);
  }

  @Override
  public Goods editGoods(Goods goods) {
    return goodsRepository.saveAndFlush(goods);
  }

  @Override
  public List<Goods> getAll() {
    return goodsRepository.findAll();
  }
}
