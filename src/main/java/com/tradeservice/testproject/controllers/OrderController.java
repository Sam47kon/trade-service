package com.tradeservice.testproject.controllers;

import com.tradeservice.testproject.entities.Goods;
import com.tradeservice.testproject.entities.Order;
import com.tradeservice.testproject.entities.OrderLine;
import com.tradeservice.testproject.services.impl.GoodsServiceImpl;
import com.tradeservice.testproject.services.impl.OrderLineServiceImpl;
import com.tradeservice.testproject.services.impl.OrderServiceImpl;
import com.tradeservice.testproject.utils.MyUtil;
import com.tradeservice.testproject.utils.OrderParams;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OrderController {

  private OrderServiceImpl orderService;
  private OrderLineServiceImpl orderLineService;
  private GoodsServiceImpl goodsService;

  @Autowired
  public OrderController(OrderServiceImpl orderService, OrderLineServiceImpl orderLineService,
      GoodsServiceImpl goodsService) {
    this.orderService = orderService;
    this.orderLineService = orderLineService;
    this.goodsService = goodsService;
  }


  /**
   * @param map - client, date, address, count, goodsname LOOK THIS!!!
   * @return text if ok
   * @throws ParseException ... todo
   */
  @PostMapping("/neworder")
  @ResponseBody
  public String addOrder(@RequestBody Map<String, String> map) throws ParseException {
    StringBuilder sb = new StringBuilder();

    Order order = new Order(map.get(OrderParams.client.toString()),
        new SimpleDateFormat(MyUtil.PATTERN_DATE_FORMAT)
            .parse(map.get(OrderParams.date.toString())),
        map.get(OrderParams.address.toString()));

    Goods goods = goodsService.getByName(map.get(OrderParams.goodsname.toString()));

    OrderLine orderLine = new OrderLine(order, goods,
        Integer.valueOf(map.get(OrderParams.count.toString())));

    int count = 0;
    for (int i = 0; i < 5; i++) {
      if (map.get(OrderParams.values()[i].toString()) != null) {
        count++;
      }
    }
    if (count == 5) {
      orderService.add(order);
      orderLineService.add(orderLine);
    }
    sb.append(order).append(goods).append(orderLine);
    return sb.toString();
  }
}
