package com.tradeservice.testproject.services.impl;

import com.tradeservice.testproject.entities.Goods;
import com.tradeservice.testproject.repositories.GoodsRepository;
import com.tradeservice.testproject.services.GoodsService;
import java.util.List;
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
    return optionalGoods.orElse(null);
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
    return goodsRepository.saveAndFlush(goods); //FIXME изменяет, только если указать id
//     Хочу чтобы изменить можно было по имени. нужно сначала найти товар по имени, и тогда его
//     изменить. 26.07 подумать, добавить новый метод или изменить этот
  }

  @Override
  public List<Goods> getAll() {
    return goodsRepository.findAll();
  }
}
