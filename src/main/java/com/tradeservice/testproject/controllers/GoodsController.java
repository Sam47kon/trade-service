package com.tradeservice.testproject.controllers;

import com.tradeservice.testproject.entities.Goods;
import com.tradeservice.testproject.services.impl.GoodsServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/catalog", method = RequestMethod.GET)
public class GoodsController {

  private GoodsServiceImpl goodsService;

  @Autowired
  public GoodsController(GoodsServiceImpl goodsService) {
    this.goodsService = goodsService;
  }

  /**
   * 6) - добавление нового товара  // РАБОТАЕТ!!!
   *
   * @param goods добавляемый товар {name:?, price:?}
   * @return сам товар, если успешно
   */
  @PostMapping("/addgoods")
  @ResponseBody
  public Goods addGoods(@RequestBody Goods goods) {
    return goodsService.add(goods);
  }

  /**
   * 9) - получение всех товаров  // РАБОТАЕТ!!!
   * @return все товары
   */
  @GetMapping(value = "")
  public List<Goods> getAllGoods() {
    return goodsService.getAll();
  }

  /**
   * 7) - изменение существующего товара
   * @param goods товар {name:?, price:?} FIXME
   * @return сам товар
   */
  @PostMapping("/editgood")
  @ResponseBody
  public Goods editGoods(@RequestBody Goods goods) {
    return goodsService.edit(goods);
  }
}
