package com.tradeservice.testproject.services;

import com.tradeservice.testproject.entities.Goods;

public interface GoodsService extends CrudService<Goods> {

  Goods getByName(String name);
}
