package com.tradeservice.TestProject.Service;

import com.tradeservice.TestProject.entities.Goods;
import java.util.List;

public interface GoodsService {

  Goods addGoods(Goods goods);

  void delete(Long id);

  Goods getByName(String name);

  Goods editGoods(Goods goods);

  List<Goods> getAll();
}
