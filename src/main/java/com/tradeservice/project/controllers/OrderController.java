package com.tradeservice.project.controllers;

import com.tradeservice.project.ecxeptions.OrderNotFoundException;
import com.tradeservice.project.entity.Order;
import com.tradeservice.project.service.OrderService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

  private OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }


  /**
   * 1) - добавление нового заказа
   *
   * @return HttpStatus.CREATED if ok, and added Order, где все данные по заказу. POST
   * http://localhost:8080/orders/ JSON
   */
  @PostMapping
  public ResponseEntity<Order> createOrder(@RequestBody Order newOrderRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(orderService.add(newOrderRequest));
  }

  /**
   * 2)	- изменение существующего заказа
   *
   * @param newOrderRequest - заказ
   * @param id id изменяемого
   * @return HttpStatus.ACCEPTED, Order, где все данные по измененному заказу. PUT
   * http://localhost:8080/orders/{id} JSON
   */
  @PutMapping("/{id}")
  public ResponseEntity<Order> updateOrder(@RequestBody Order newOrderRequest,
      @PathVariable Long id) {
    return ResponseEntity.accepted().body(orderService.update(newOrderRequest, id));
  }

  /**
   * 3)	- удаление заказа
   *
   * @param id - id заказа (Order)
   * @return HttpStatus.ACCEPTED DELETE http://localhost:8080/orders/{id}
   */
  @DeleteMapping("/{id}")
  public ResponseEntity deleteOrder(@PathVariable Long id) {
    orderService.delete(id);
    return ResponseEntity.accepted().build();
  }

  /**
   * 4)	- получение всех заказов
   *
   * @return HttpStatus.OK, Collection всех заказов GET http://localhost:8080/orders/
   */
  @GetMapping
  public ResponseEntity<Collection<Order>> getAllOrders() {
    return ResponseEntity.ok(orderService.getAll());
  }

  /**
   * 5)	- получение определенного заказа по id
   *
   * @param id - id Order
   * @return - HttpStatus.OK, Order GET http://localhost:8080/orders/{id}
   */
  @GetMapping("/{id}")
  public ResponseEntity<Order> getOrder(@PathVariable Long id) {
    return ResponseEntity
        .ok(orderService.getById(id).orElseThrow(() -> new OrderNotFoundException(id)));
  }
}
