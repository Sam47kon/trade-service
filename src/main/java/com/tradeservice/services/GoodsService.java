package com.tradeservice.services;

import com.tradeservice.entities.Goods;
import java.util.List;

public interface GoodsService {

  Goods add(Goods goods);

  Goods getById(Long id);

  Goods edit(Goods newGoods, Long id);

  void delete(Long id);

  List<Goods> getAll();

  Goods getByName(String name);
}
