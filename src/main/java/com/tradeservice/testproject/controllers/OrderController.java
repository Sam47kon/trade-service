package com.tradeservice.testproject.controllers;

import com.tradeservice.testproject.entities.OrderLine;
import com.tradeservice.testproject.services.OrderService;
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
@RequestMapping("/orders")
public class OrderController {

  private OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }


  /**
   * 1) - добавление нового заказа
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

  /**
   * 2)	- изменение существующего заказа
   *
   * @return OrderLine, где все данные по заказу.
   *
   * http://localhost:8080/orders/ PUT JSON:
   * @see com.tradeservice.testproject.utils editFullOrder.json
   */
  @PutMapping("/")
  @ResponseBody
  public OrderLine editFullOrder(@RequestBody OrderLine orderLine) {
    return orderService.editFullOrder(orderLine);
  }

  /**
   * 3)	- удаление заказа
   *
   * @param id - id заказа (OrderLine)
   * @return - "удалено" при успехе
   *
   * http://localhost:8080/orders/{id} DELETE
   */
  @DeleteMapping("/{id}")
  @ResponseBody
  public String deleteFullOrder(@PathVariable Long id) {
    orderService.deleteFullOrder(id);
    return "Удалено";
  }

  /**
   * 4)	- получение всех заказов
   *
   * http://localhost:8080/orders GET
   */
  @GetMapping("")
  public List<OrderLine> getAllFullOrders() {
    return orderService.getAllOrderLines();
  }

  /**
   * 5)	- получение определенного заказа по id
   *
   * @param id - id OrderLine
   * @return - OrderLine
   * @throws NoSuchElementException (Такого Заказа нет)
   *
   * http://localhost:8080/orders/{id} GET
   */
  @GetMapping("/{id}")
  @ResponseBody
  public OrderLine getOrderLineById(@PathVariable Long id) {
    return orderService.getOrderLineById(id);
  }
}
