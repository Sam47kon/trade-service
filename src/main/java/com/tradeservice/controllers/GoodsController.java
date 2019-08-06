package com.tradeservice.controllers;

import com.tradeservice.services.GoodsService;
import com.tradeservice.entities.Goods;
import java.util.List;
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
@RequestMapping(value = "/goods")
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
   * POST http://localhost:8080/goods/ JSON:
   * @see com.tradeservice.utils addGoods.json
   */
  @PostMapping
  @ResponseBody
  public Goods addGoods(@RequestBody Goods goods) {
    return goodsService.add(goods);
  }

  /**
   * 7) - изменение существующего товара, поиск в базе по id
   * @param newGoods товар
   * @return сам товар
   *
   * PUT http://localhost:8080/goods/{id} JSON:
   * @see com.tradeservice.utils editGoods.json
   */
  @PutMapping("/{id}")
  @ResponseBody
  public Goods editGoods(@RequestBody Goods newGoods, @PathVariable Long id) {
    return goodsService.edit(newGoods, id);
  }

  /**
   * 8)	- удаление товара
   * @param id - id товара
   *
   * DELETE http://localhost:8080/goods/{id}
   */
  @DeleteMapping("/{id}")
  @ResponseBody
  public void deleteGoods(@PathVariable Long id) {
    goodsService.delete(id);
  }

  /**
   * 9) - получение всех товаров
   * @return List все товары
   *
   * GET http://localhost:8080/goods
   */
  @GetMapping
  public List<Goods> getAllGoods() {
    return goodsService.getAll();
  }

  /**
   * 10)	- получение определенного товара по id
   * @param id - id товара
   * @return товар, если найден
   *
   * GET http://localhost:8080/catalog/{id}
   */
  @GetMapping("/{id}")
  @ResponseBody
  public Goods getGoodsById(@PathVariable Long id) {
    return goodsService.getById(id);
  }
}
