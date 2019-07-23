package com.tradeservice.testproject.controllers;

import com.tradeservice.testproject.entities.Goods;
import com.tradeservice.testproject.services.impl.GoodsServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/catalog", method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
public class GoodsController {

  private GoodsServiceImpl goodsService;

  @Autowired
  public GoodsController(GoodsServiceImpl goodsService) {
    this.goodsService = goodsService;
  }

  @GetMapping(value = "")
  public List<Goods> getAllGoods() {
    return goodsService.getAll();
  }


  @GetMapping("/test1")
  public String getAllGoodsTest1() {
    List<Goods> list = goodsService.getAll();
    return String.valueOf(list.size());
  }

  @PostMapping(value = "/test2", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String test2(@RequestParam String name,@RequestParam(value = "price") Double price) {
    return name + price;
  }


  @PostMapping(value = "/editgoods")    // TODO
  @ResponseBody
  public Goods editGoods(@RequestParam(value = "id") Long id,
      @RequestParam(value = "name") String name,
      @RequestParam(value = "price") Double price) {

    Goods goods = goodsService.getById(id);
    return goodsService.edit(goods);
  }

  @PostMapping(value = "/addgoods")
  @ResponseBody
  public Goods addGoods(@RequestParam String name, @RequestParam Double price) {
    return goodsService.add(new Goods(name, price));
  }


  @GetMapping("/test")
  public String getString() {
    return "Получилось!";
  }
}
