package com.tradeservice.TestProject.Controllers;

import com.tradeservice.TestProject.Dao.OrderDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order")
public class OrderController {  // TODO

  OrderDAO orderDAO;
}
