package com.tradeservice.testproject.controllers;

import com.tradeservice.testproject.entities.Goods;
import com.tradeservice.testproject.services.GoodsService;
import com.tradeservice.testproject.services.impl.GoodsServiceImpl;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/catalog")
public class GoodsController {

  private GoodsService goodsService;

  @Autowired
  public GoodsController(GoodsService goodsService) {
    this.goodsService = goodsService;
  }


  /**
   * 6) - добавление нового товара  // ГОТОВО
   *
   * @param goods добавляемый товар {name:?, price:?}
   * @return сам товар, если успешно
   *
   *  http://localhost:8080/catalog/ POST JSON:
   * {
   *     "name": "Товар 1",
   *     "price": 999
   * }
   */
  @PostMapping("/")
  @ResponseBody
  public Goods addGoods(@RequestBody Goods goods) {
    return goodsService.add(goods);
  }

  /**
   * 7) - изменение существующего товара, поиск в базе по id // ГОТОВО
   *
   * @param goods товар {goodId:?, name:?, price:?}
   * @return сам товар, или null если не найдено.
   *
   *  http://localhost:8080/catalog/ PUT JSON:
   * {
   *     "goodsId": 1,
   *     "name": "Товар 1 изменен",
   *     "price": 444
   * }
   */
  @PutMapping("/")
  @ResponseBody
  public Goods editGoods(@RequestBody Goods goods) {
      return goodsService.edit(goods);
  }

  /**
   * 8)	- удаление товара // ГОТОВО
   *
   * @param id - id товара
   * @return "Удалено", при успехе http://localhost:8080/catalog/ DELETE JSON: 1 (или
   * другой id)
   */
  @DeleteMapping("/")
  @ResponseBody
  public String deleteGoods(@RequestBody Long id) {
    goodsService.delete(id);
    return "Удалено";
  }

  /**
   * 9) - получение всех товаров  // ГОТОВО
   * @return List все товары
   *
   *  http://localhost:8080/catalog GET
   */
  @GetMapping("")
  public List<Goods> getAllGoods() {
    return goodsService.getAll();
  }

  /**
   * 10)	- получение определенного товара по id // ГОТОВО
   *
   * @param id - id товара
   * @return товар, если найден
   * @throws NoSuchElementException (Такого товара нет!)
   *
   * http://localhost:8080/catalog/ GET + JSON: 1 (или любой id)
   */
  @GetMapping("/")
  @ResponseBody
  public Goods getGoodsById(@RequestBody Long id) {
    return goodsService.getById(id);
  }
}
