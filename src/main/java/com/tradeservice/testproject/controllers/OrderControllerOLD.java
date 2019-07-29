//package com.tradeservice.testproject.controllers;
//
//import com.tradeservice.testproject.entities.OrderLine;
//import com.tradeservice.testproject.services.OrderServiceOLD;
//import java.util.List;
//import java.util.Map;
//import java.util.NoSuchElementException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/orders")
//public class OrderControllerOLD {
//
//  private OrderServiceOLD orderService;
//
//  @Autowired
//  public OrderControllerOLD(OrderServiceOLD orderService) {
//    this.orderService = orderService;
//  }
//
//
//  /**
//   * 1) - добавление нового заказа // ГОТОВО
//   *
//   * @param map - {client:?, date:?, address:?, count:?, goodsName:?}. count - default = 1
//   * @return map, где все данные по заказу.
//   *
//   * http://localhost:8080/orders/ POST JSON: { "client": "Клиент 1", "date": "1995/11/30",
//   * "address": "адрес доставки 1", "count": 5, "goodsName": "Товар 1" }
//   */
//  @PostMapping("/")
//  @ResponseBody
//  public OrderLine addFullOrder(@RequestBody Map<String, String> map) {
//    return orderService.addFullOrder(map);
//  }
//
//  /**
//   * 2)	- изменение существующего заказа // ГОТОВО
//   *
//   * @param map - {id, client:?, date:?, address:?, count:?, goodsName:?}. id - orderLine
//   * @return map, где все данные по заказу.
//   *
//   * http://localhost:8080/orders/ PUT JSON: { "id": 1, "client": "Клиент 1 изменено", "date":
//   * "2005/11/30", "address": "адрес доставки 1 изменен", "count": 999, "goodsName": "Товар 1" }
//   */
//  @PutMapping("/")
//  @ResponseBody
//  public OrderLine editFullOrder(@RequestBody Map<String, String> map) {
//    return orderService.editFullOrder(map);
//  }
//
//  /**
//   * 3)	- удаление заказа  // ГОТОВО
//   *
//   * @param id - id заказа (OrderLine)
//   * @return - "удалено" при успехе
//   *
//   * http://localhost:8080/orders/ DELETE JSON: 1 (или другой id)
//   */
//  @DeleteMapping("/")
//  @ResponseBody
//  public String deleteFullOrder(@RequestBody Long id) {
//    orderService.deleteFullOrder(id);
//    return "Удалено";
//  }
//
//  /**
//   * 4)	- получение всех заказов  // ГОТОВО
//   *
//   * http://localhost:8080/orders GET
//   */
//  @GetMapping("")
//  public List<OrderLine> getAllFullOrders() {
//    return orderService.getAllOrderLines();
//  }
//
//  /**
//   * 5)	- получение определенного заказа по id // ГОТОВО
//   *
//   * @param id - id OrderLine
//   * @return - OrderLine
//   * @throws NoSuchElementException (Такого Заказа нет)
//   *
//   * http://localhost:8080/orders/ GET + JSON: 1 (или другой id)
//   */
//  @GetMapping("/")
//  @ResponseBody
//  public OrderLine getOrderLineById(@RequestBody Long id) {
//    return orderService.getOrderLineById(id);
//  }
//}
