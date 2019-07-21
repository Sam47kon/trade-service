package com.tradeservice.TestProject.Controllers;

import com.tradeservice.TestProject.Dao.OrderLineDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order-line")
public class OrderLineController {  // TODO

  OrderLineDAO orderLineDAO;
}
