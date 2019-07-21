package com.tradeservice.TestProject.Controllers;

import com.tradeservice.TestProject.Dao.GoodsDAO;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("catalog")
public class GoodsController {  // TODO почему запрос ошибка?

  GoodsDAO goodsDAO;

  @GetMapping("goods")
  ModelAndView list() {
    return new ModelAndView("catalog/goods", Map.of("goods", this.goodsDAO.findAll()),
        HttpStatus.OK);
  }
}
