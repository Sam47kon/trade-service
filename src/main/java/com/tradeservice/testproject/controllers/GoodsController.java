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

  @GetMapping("/")
  public List<Goods> getGoods() {
    return goodsService.getAll();
  }

  @PostMapping("/editgoods")
  public Goods editGoods(Goods goods) {// TODO
    return goodsService.edit(goods);
  }


  @PostMapping(value = "/addgoods", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public Goods getGoods(Goods goods) {
    return goodsService.add(goods);
  }


  @GetMapping("/test")
  public String getString() {
    return "Получилось! \n" + "package com.tradeservice.testproject.controllers;\n"
        + "\n"
        + "import org.springframework.web.bind.annotation.RequestMapping;\n"
        + "import org.springframework.web.bind.annotation.RestController;\n"
        + "\n"
        + "@RestController\n"
        + "@RequestMapping(\"catalog\")\n"
        + "public class GoodsController {\n"
        + "\n"
        + "  @RequestMapping(\"get\")\n"
        + "  public String getString(){\n"
        + "    return \"\";\n"
        + "  }\n"
        + "}";
  }
}
