package com.tradeservice.testproject.util;

import com.tradeservice.testproject.entities.Goods;

public class GoodsUtil {

  public static Goods createGoods() {
    Goods goods = new Goods();
    goods.setGoodsId(5L);
    goods.setName("Test Goods");
    goods.setPrice(9D);
    return goods;
  }
}
