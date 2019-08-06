package com.tradeservice.testproject.controllers;

import com.tradeservice.testproject.entities.OrderLine;
import com.tradeservice.testproject.services.OrderEntryService;
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
public class OrderEnrtyController {

  private OrderEntryService orderEntryService;

  @Autowired
  public OrderEnrtyController(OrderEntryService orderEntryService) {
    this.orderEntryService = orderEntryService;
  }


  /**
   * 1) - добавление нового заказа
   *
   * @return OrderLine, где все данные по заказу.
   *
   * POST http://localhost:8080/orders/ JSON:
   * @see com.tradeservice.testproject.utils addFullOrder.json
   */
  @PostMapping
  @ResponseBody
  public OrderLine addFullOrder(@RequestBody OrderLine orderLine) {
    return orderEntryService.addFullOrder(orderLine);
  }

  /**
   * 2)	- изменение существующего заказа
   *
   * @return OrderLine, где все данные по заказу.
   *
   * PUT http://localhost:8080/orders/{id} JSON:
   * @see com.tradeservice.testproject.utils editFullOrder.json
   */
  @PutMapping("/{id}")
  @ResponseBody
  public OrderLine editFullOrder(@RequestBody OrderLine newOrderLine, @PathVariable Long id) {
    return orderEntryService.editFullOrder(newOrderLine, id);
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
    orderEntryService.deleteFullOrder(id);
  }

  /**
   * 4)	- получение всех заказов
   *
   * GET http://localhost:8080/orders/
   */
  @GetMapping
  public List<OrderLine> getAllFullOrders() {
    return orderEntryService.getAllOrderLines();
  }

  /**
   * 5)	- получение определенного заказа по id
   *
   * @param id - id OrderLine
   * @return - OrderLine
   * @throws NoSuchElementException (Такого Заказа нет)
   *
   * GET http://localhost:8080/orders/{id}
   */
  @GetMapping("/{id}")
  @ResponseBody
  public OrderLine getOrderLineById(@PathVariable Long id) {
    return orderEntryService.getOrderLineById(id);
  }
}
