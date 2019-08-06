package com.tradeservice.controllers;

import com.tradeservice.ecxeptions.OrderEntryNotFoundException;
import com.tradeservice.entities.Order;
import com.tradeservice.services.OrderService;
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
   * @return Order, где все данные по заказу.
   *
   * POST http://localhost:8080/orders/ JSON:
   * @see com.tradeservice.utils addFullOrder.json
   */
  @PostMapping
  @ResponseBody
  public Order addFullOrder(@RequestBody Order order) {
    return orderService.add(order);
  }

  /**
   * 2)	- изменение существующего заказа
   *
   * @return Order, где все данные по измененному заказу.
   *
   * PUT http://localhost:8080/orders/{id} JSON:
   * @see com.tradeservice.utils editFullOrder.json
   */
  @PutMapping("/{id}")
  @ResponseBody
  public Order editFullOrder(@RequestBody Order newOrder, @PathVariable Long id) {
    return orderService.edit(newOrder, id);
  }

  /**
   * 3)	- удаление заказа
   *
   * @param id - id заказа (OrderLine)
   *
   * DELETE http://localhost:8080/orders/{id}
   */
  @DeleteMapping("/{id}")
  @ResponseBody
  public void deleteFullOrder(@PathVariable Long id) {
    orderService.delete(id);
  }

  /**
   * 4)	- получение всех заказов
   *
   * GET http://localhost:8080/orders/
   */
  @GetMapping
  public List<Order> getAllFullOrders() {
    return orderService.getAll();
  }

  /**
   * 5)	- получение определенного заказа по id
   *
   * @param id - id OrderLine
   * @return - Order
   *
   * GET http://localhost:8080/orders/{id}
   */
  @GetMapping("/{id}")
  @ResponseBody
  public Order getOrderLineById(@PathVariable Long id) {
    return orderService.getById(id).orElseThrow(() -> new OrderEntryNotFoundException(id));
  }
}
