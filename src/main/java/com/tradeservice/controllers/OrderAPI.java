package com.tradeservice.controllers;

import com.tradeservice.ecxeptions.OrderEntryNotFoundException;
import com.tradeservice.entities.Order;
import com.tradeservice.services.OrderService;
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
@RequestMapping("/orders")
public class OrderAPI {

  private OrderService orderService;

  @Autowired
  public OrderAPI(OrderService orderService) {
    this.orderService = orderService;
  }


  /**
   * 1) - добавление нового заказа
   * @return HttpStatus.CREATED if ok, and added Order, где все данные по заказу.
   * POST http://localhost:8080/orders/ JSON:
   * @see com.tradeservice.utils addFullOrder.json
   */
  @PostMapping
  public ResponseEntity<Order> addFullOrder(@RequestBody Order newOrderRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(orderService.add(newOrderRequest));
  }

  /**
   * 2)	- изменение существующего заказа
   * @param newOrderRequest - заказ
   * @param id id изменяемого
   * @return HttpStatus.ACCEPTED, Order, где все данные по измененному заказу.
   * PUT http://localhost:8080/orders/{id} JSON:
   * @see com.tradeservice.utils editFullOrder.json
   */
  @PutMapping("/{id}")
  public ResponseEntity<Order> editFullOrder(@RequestBody Order newOrderRequest,
      @PathVariable Long id) {
    return ResponseEntity.accepted().body(orderService.edit(newOrderRequest, id));
  }

  /**
   * 3)	- удаление заказа
   * @param id - id заказа (Order)
   * @return HttpStatus.ACCEPTED
   * DELETE http://localhost:8080/orders/{id}
   */
  @DeleteMapping("/{id}")
  public ResponseEntity deleteFullOrder(@PathVariable Long id) {
    orderService.delete(id);
    return ResponseEntity.accepted().build();
  }

  /**
   * 4)	- получение всех заказов
   * @return HttpStatus.OK, List всех заказов
   * GET http://localhost:8080/orders/
   */
  @GetMapping
  public ResponseEntity<List<Order>> getAllFullOrders() {
    return ResponseEntity.ok(orderService.getAll());
  }

  /**
   * 5)	- получение определенного заказа по id
   * @param id - id Order
   * @return - HttpStatus.OK, Order
   * GET http://localhost:8080/orders/{id}
   */
  @GetMapping("/{id}")
  public ResponseEntity<Order> getOrderLineById(@PathVariable Long id) {
    return ResponseEntity
        .ok(orderService.getById(id).orElseThrow(() -> new OrderEntryNotFoundException(id)));
  }
}
