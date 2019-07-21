package com.tradeservice.TestProject.util;

import com.tradeservice.TestProject.entities.Goods;

public class GoodsUtil {

  public static Goods createGoods() {
    Goods goods = new Goods();
    goods.setName("Test Goods");
    return goods;
  }
}
