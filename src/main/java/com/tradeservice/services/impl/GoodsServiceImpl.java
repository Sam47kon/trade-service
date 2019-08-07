package com.tradeservice.services.impl;

import com.tradeservice.services.GoodsService;
import com.tradeservice.ecxeptions.GoodsNotFoundException;
import com.tradeservice.entities.Goods;
import com.tradeservice.repositories.GoodsRepository;
import java.util.Collection;
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
  public Goods getById(Long id) {
    return goodsRepository.findById(id).orElseThrow(() -> new GoodsNotFoundException(id));
  }

  @Override
  public void delete(Long id) {
    if (id == null) {
      throw new GoodsNotFoundException(id);
    }
    goodsRepository.findById(id).map(goods -> {
      goodsRepository.deleteById(goods.getGoodsId());
      return 1;
    }).orElseThrow(() -> new GoodsNotFoundException(id));
  }

  @Override
  public Goods getByName(String name) {
    return goodsRepository.findByName(name);
  }

  @Override
  public Collection<Goods> getAllById(Collection<Long> collection) {
    return goodsRepository.findAllById(collection);
  }

  @Override
  public Goods edit(Goods newGoods, Long id) {
    return goodsRepository.findById(id)
        .map(goods -> {
          goods.setName(newGoods.getName());
          goods.setPrice(newGoods.getPrice());
          return goodsRepository.saveAndFlush(goods);
        }).orElseThrow(() -> new GoodsNotFoundException(id));
  }

  @Override
  public List<Goods> getAll() {
    return goodsRepository.findAll();
  }
}
