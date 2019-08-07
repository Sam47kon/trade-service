package com.tradeservice.services;

import com.tradeservice.entities.Goods;
import java.util.List;
import java.util.Optional;

public interface GoodsService {

  Goods add(Goods goods);

  Goods edit(Goods newGoods, Long id);

  void delete(Long id);

  List<Goods> getAll();

  Optional<Goods> getById(Long id);
}
