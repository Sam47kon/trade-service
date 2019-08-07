package com.tradeservice.aop;

import com.tradeservice.ecxeptions.GoodsNotFoundException;
import com.tradeservice.ecxeptions.OrderEntryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(GoodsNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String goodsNotFoundHandler(GoodsNotFoundException ex) {
    return ex.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(OrderEntryNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String orderEntryNotFoundHandler(OrderEntryNotFoundException ex) {
    return ex.getMessage();
  }
}
