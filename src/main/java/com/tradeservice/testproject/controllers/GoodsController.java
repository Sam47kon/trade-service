package com.tradeservice.testproject.controllers;

import com.tradeservice.testproject.entities.Goods;
import com.tradeservice.testproject.services.impl.GoodsServiceImpl;
import java.util.List;
import java.util.Map;
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
   * 6) - добавление нового товара  // ГОТОВО
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
   * 7) - изменение существующего товара, поиск в базе по id // ГОТОВО
   * @param goods товар {goodId:?, name:?, price:?}
   * @return сам товар, или null если не найдено.
   */
  @PostMapping("/editgoods")
  @ResponseBody
  public Goods editGoods(@RequestBody Goods goods) {
    if (goods.getGoodsId() != null) {
      return goodsService.edit(goods);
    }
    return null;
  }

  /**
   * 7.1) - изменение существующего товара, поиск в базе по имени товара. // ГОТОВО
   *
   * @param map {oldName:?, newName:?, newPrice:?}. newName и newPrice - опционально
   * @return сам товар, или null если не найдено.
   */
  @PostMapping("/editgoodsbyname")
  @ResponseBody
  public Goods editGoodsByName(@RequestBody Map<String, String> map) {
    return goodsService.editWithoutId(map);
  }

  /**
   * 9) - получение всех товаров  // ГОТОВО
   * @return все товары
   */
  @GetMapping(value = "")
  public List<Goods> getAllGoods() {
    return goodsService.getAll();
  }


}
