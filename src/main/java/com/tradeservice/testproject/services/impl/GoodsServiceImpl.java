package com.tradeservice.testproject.services.impl;

import com.tradeservice.testproject.entities.Goods;
import com.tradeservice.testproject.repositories.GoodsRepository;
import com.tradeservice.testproject.services.GoodsService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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
  public Goods getById(Long id) {
    Optional<Goods> optionalGoods = goodsRepository.findById(id);
    if (optionalGoods.isEmpty()) {
      throw new NoSuchElementException("Такого товара нет! ");
    }
    return optionalGoods.get();
  }

  @Override
  public void delete(Long id) {
    if (id == null) {
      throw new NullPointerException("Не указан goodsId");
    }
    goodsRepository.deleteById(id);
  }

  @Override
  public Goods getByName(String name) {
    return goodsRepository.findByName(name);
  }

  @Override
  public Goods edit(Goods goods) {
    if (goods.getGoodsId() == null) {
      throw new NullPointerException("Не указан goodsId");
    }
    return goodsRepository.saveAndFlush(goods);
  }

  @Override
  public List<Goods> getAll() {
    return goodsRepository.findAll();
  }
}
