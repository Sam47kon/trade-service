package com.tradeservice.testproject.services.impl;

import com.tradeservice.testproject.services.GoodsService;
import com.tradeservice.testproject.entities.Goods;
import com.tradeservice.testproject.repositories.GoodsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {

  private GoodsRepository goodsRepository;

  @Autowired
  public GoodsServiceImpl(GoodsRepository goodsRepository) {
    this.goodsRepository = goodsRepository;
  }

  @Override
  public Goods add(Goods goods) {
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
  public Goods edit(Goods goods) {
    return goodsRepository.saveAndFlush(goods);
  }

  @Override
  public List<Goods> getAll() {
    return goodsRepository.findAll();
  }
}
