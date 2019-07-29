package com.tradeservice.testproject.controllers;

import com.tradeservice.testproject.entities.Goods;
import com.tradeservice.testproject.services.GoodsService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
   * 6) - добавление нового товара
   * @param goods добавляемый товар
   * @return сам товар, если успешно (но только с id goods)
   *
   * http://localhost:8080/catalog/ POST JSON:
   * @see com.tradeservice.testproject.utils addGoods.json
   */
  @PostMapping("/")
  @ResponseBody
  public Goods addGoods(@RequestBody Goods goods) {
    return goodsService.add(goods);
  }

  /**
   * 7) - изменение существующего товара, поиск в базе по id
   * @param goods товар {goodId:?, name:?, price:?}
   * @return сам товар, или null если не найдено.
   *
   *  http://localhost:8080/catalog/ PUT JSON:
   * @see com.tradeservice.testproject.utils editGoods.json
   */
  @PutMapping("/")
  @ResponseBody
  public Goods editGoods(@RequestBody Goods goods) {
      return goodsService.edit(goods);
  }

  /**
   * 8)	- удаление товара
   * @param id - id товара
   * @return "Удалено", при успехе
   *
   * http://localhost:8080/catalog/{id} DELETE
   */
  @DeleteMapping("/{id}")
  @ResponseBody
  public String deleteGoods(@PathVariable Long id) {
    goodsService.delete(id);
    return "Удалено";
  }

  /**
   * 9) - получение всех товаров
   * @return List все товары
   *
   *  http://localhost:8080/catalog GET
   */
  @GetMapping("")
  public List<Goods> getAllGoods() {
    return goodsService.getAll();
  }

  /**
   * 10)	- получение определенного товара по id
   * @param id - id товара
   * @return товар, если найден
   * @throws NoSuchElementException (Такого товара нет!)
   *
   * http://localhost:8080/catalog/{id} GET
   */
  @GetMapping("/{id}")
  @ResponseBody
  public Goods getGoodsById(@PathVariable Long id) {
    return goodsService.getById(id);
  }
}
