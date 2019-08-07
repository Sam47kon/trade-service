package com.tradeservice.services.impl;

import com.tradeservice.ecxeptions.GoodsNotFoundException;
import com.tradeservice.entities.Goods;
import com.tradeservice.repositories.GoodsRepository;
import com.tradeservice.services.GoodsService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {

  private final GoodsRepository goodsRepository;

  @Autowired
  public GoodsServiceImpl(GoodsRepository goodsRepository) {
    this.goodsRepository = goodsRepository;
  }


  @Override
  public Goods add(Goods goods) {
    return goodsRepository.save(goods);
  }

  @Override
  public Goods edit(Goods newGoods, Long id) {
    return goodsRepository.findById(id)
        .map(goods -> {
          if (newGoods.getName() != null) {
            goods.setName(newGoods.getName());
          }
          if (newGoods.getPrice() != null) {
            goods.setPrice(newGoods.getPrice());
          }
          return goodsRepository.saveAndFlush(goods);
        }).orElseThrow(() -> new GoodsNotFoundException(id));
  }

  @Override
  public void delete(Long id) {
    goodsRepository.findById(id).orElseThrow(() -> new GoodsNotFoundException(id));
    goodsRepository.deleteById(id);
  }

  @Override
  public List<Goods> getAll() {
    return goodsRepository.findAll();
  }

  @Override
  public Optional<Goods> getById(Long id) {
    return goodsRepository.findById(id);
  }
}
