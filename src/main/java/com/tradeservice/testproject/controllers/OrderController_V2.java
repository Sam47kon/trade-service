package com.tradeservice.testproject.controllers;

import com.tradeservice.testproject.entities.Goods;
import com.tradeservice.testproject.entities.Order;
import com.tradeservice.testproject.entities.OrderLine;
import com.tradeservice.testproject.services.OrderService_V2;
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
@RequestMapping("/orders")
public class OrderController_V2 {

  private OrderService_V2 orderService;

  @Autowired
  public OrderController_V2(OrderService_V2 orderService) {
    this.orderService = orderService;
  }


  /**
   * 1) - добавление нового заказа // ГОТОВО
   *
   * @return OrderLine, где все данные по заказу.
   *
   * http://localhost:8080/orders/ POST JSON:
   * @see com.tradeservice.testproject.utils addFullOrder.json
   */
  @PostMapping("/")
  @ResponseBody
  public OrderLine addFullOrder(@RequestBody OrderLine orderLine) {
    return orderService.addFullOrder(orderLine);
  }
  // TODO TODO TODO TODO  ОСТАНОВИЛСЯ ЗДЕСЬ
  /**
   * 2)	- изменение существующего заказа // ГОТОВО
   *
   * - {id, client:?, date:?, address:?, count:?, goodsName:?}. id - orderLine
   *
   * @return map, где все данные по заказу.
   *
   * http://localhost:8080/orders/ PUT JSON: { "id": 1, "client": "Клиент 1 изменено", "date":
   * "2005/11/30", "address": "адрес доставки 1 изменен", "count": 999, "goodsName": "Товар 1" }
   */
  @PutMapping("/")
  @ResponseBody
  public OrderLine editFullOrder(Long id, @RequestBody Order order, @RequestBody Goods goods,
      Integer count) {
    return orderService.editFullOrder(id, order, goods, count);
  }

  /**
   * 3)	- удаление заказа  // ГОТОВО
   *
   * @param id - id заказа (OrderLine)
   * @return - "удалено" при успехе
   *
   * http://localhost:8080/orders/ DELETE JSON: 1 (или другой id)
   */
  @DeleteMapping("/")
  @ResponseBody
  public String deleteFullOrder(@RequestBody Long id) {
    orderService.deleteFullOrder(id);
    return "Удалено";
  }

  /**
   * 4)	- получение всех заказов  // ГОТОВО
   *
   * http://localhost:8080/orders GET
   */
  @GetMapping("")
  public List<OrderLine> getAllFullOrders() {
    return orderService.getAllOrderLines();
  }

  /**
   * 5)	- получение определенного заказа по id // ГОТОВО
   *
   * @param id - id OrderLine
   * @return - OrderLine
   * @throws NoSuchElementException (Такого Заказа нет)
   *
   * http://localhost:8080/orders/ GET + JSON: 1 (или другой id)
   */
  @GetMapping("/")
  @ResponseBody
  public OrderLine getOrderLineById(@RequestBody Long id) {
    return orderService.getOrderLineById(id);
  }
}
