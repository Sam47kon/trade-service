package com.tradeservice.testproject.services;

import com.tradeservice.testproject.entities.Goods;
import java.util.List;

public interface GoodsService {

  Goods add(Goods goods);

  Goods getById(Long id);

  Goods edit(Goods goods);

  void delete(Long id);

  List<Goods> getAll();

  Goods getByName(String name);
}
