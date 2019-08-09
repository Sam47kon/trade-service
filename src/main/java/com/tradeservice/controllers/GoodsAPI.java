package com.tradeservice.controllers;

import com.tradeservice.ecxeptions.GoodsNotFoundException;
import com.tradeservice.entities.Goods;
import com.tradeservice.services.GoodsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/goods")
public class GoodsAPI {

  private GoodsService goodsService;

  @Autowired
  public GoodsAPI(GoodsService goodsService) {
    this.goodsService = goodsService;
  }


  /**
   * 6) - добавление нового товара
   * @param goods добавляемый товар
   * @return HttpStatus.CREATED if ok, and added Goods
   * POST http://localhost:8080/goods/ JSON:
   * @see com.tradeservice.utils addGoods.json
   */
  @PostMapping
  public ResponseEntity<Goods> addGoods(@RequestBody Goods goods) {
    return ResponseEntity.status(HttpStatus.CREATED).body(goodsService.add(goods));
  }

  /**
   * 7) - изменение существующего товара, поиск в базе по id
   * @param newGoods товар
   * @param id id изменяемого
   * @return HttpStatus.ACCEPTED, сам товар, измененный
   * PUT http://localhost:8080/goods/{id} JSON:
   * @see com.tradeservice.utils editGoods.json
   */
  @PutMapping("/{id}")
  public ResponseEntity<Goods> editGoods(@RequestBody Goods newGoods, @PathVariable Long id) {
    return ResponseEntity.accepted().body(goodsService.edit(newGoods, id));
  }

  /**
   * 8)	- удаление товара
   * @param id - id товара
   * @return HttpStatus.ACCEPTED
   * DELETE http://localhost:8080/goods/{id}
   */
  @DeleteMapping("/{id}")
  public ResponseEntity deleteGoods(@PathVariable Long id) {
    goodsService.delete(id);
    return ResponseEntity.accepted().build();
  }

  /**
   * 9) - получение всех товаров
   * @return HttpStatus.OK, List все товары
   * GET http://localhost:8080/goods
   */
  @GetMapping
  public ResponseEntity<List<Goods>> getAllGoods() {
    return ResponseEntity.ok(goodsService.getAll());
  }

  /**
   * 10)	- получение определенного товара по id
   * @param id - id товара
   * @return HttpStatus.OK, товар, если найден
   * GET http://localhost:8080/catalog/{id}
   */
  @GetMapping("/{id}")
  public ResponseEntity<Goods> getGoodsById(@PathVariable Long id) {
    return ResponseEntity
        .ok(goodsService.getById(id).orElseThrow(() -> new GoodsNotFoundException(id)));
  }
}
