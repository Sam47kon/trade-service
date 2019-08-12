package com.tradeservice.project.controllers;


import static com.tradeservice.project.util.TestConstants.NOT_EXIST_ID;
import static com.tradeservice.project.util.TestConstants.ORDER;
import static com.tradeservice.project.util.TestConstants.ORDERS_ARRAY_JSON;
import static com.tradeservice.project.util.TestConstants.ORDER_JSON;
import static com.tradeservice.project.util.TestConstants.ORDER_TO_UPDATE;
import static com.tradeservice.project.util.TestConstants.ORDER_UPDATED_JSON;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tradeservice.project.ecxeptions.OrderNotFoundException;
import com.tradeservice.project.ecxeptions.ProductNotFoundException;
import com.tradeservice.project.entity.Order;
import com.tradeservice.project.service.impl.OrderServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
class OrderControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private OrderServiceImpl orderService;

  @AfterEach
  void after() {
    verifyNoMoreInteractions(orderService);
  }

  @Test
  void testCreateOrder() throws Exception {
    when(orderService.add(ORDER)).thenReturn(ORDER);

    mockMvc.perform(post("/orders").contentType(MediaType.APPLICATION_JSON)
        .content(ORDER_JSON)).andDo(print()).andExpect(status().isCreated())
        .andExpect(jsonPath("$.orderId").value(1L))
        .andExpect(content().json(ORDER_JSON));

    verify(orderService).add(ORDER);
  }

  @Test
  void testCreateOrder_fail_not_exist_product() throws Exception {
    doThrow(ProductNotFoundException.class).when(orderService).add(ORDER);

    mockMvc.perform(post("/orders").contentType(MediaType.APPLICATION_JSON)
        .content(ORDER_JSON)).andDo(print()).andExpect(status().isNotFound());

    verify(orderService).add(ORDER);
  }

  @Test
  void testUpdateOrder() throws Exception {
    when(orderService.update(ORDER_TO_UPDATE, ORDER.getOrderId())).thenReturn(ORDER_TO_UPDATE);

    mockMvc.perform(put("/orders/" + ORDER.getOrderId()).content(ORDER_UPDATED_JSON)
        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
        .andExpect(status().isAccepted())
        .andExpect(jsonPath("$.clientName").value(ORDER_TO_UPDATE.getClientName()))
        .andExpect(content().json(ORDER_UPDATED_JSON));

    verify(orderService, times(1)).update(ORDER_TO_UPDATE, ORDER.getOrderId());
  }

  @Test
  void testUpdateOrder_fail_not_found() throws Exception {
    when(orderService.update(ORDER_TO_UPDATE, NOT_EXIST_ID))
        .thenThrow(OrderNotFoundException.class);

    mockMvc.perform(put("/orders/" + NOT_EXIST_ID).content(ORDER_UPDATED_JSON)
        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
        .andExpect(status().isNotFound());

    verify(orderService, times(1)).update(ORDER_TO_UPDATE, NOT_EXIST_ID);
  }

  @Test
  void testDeleteOrder() throws Exception {
    doNothing().when(orderService).delete(ORDER.getOrderId());
    doThrow(OrderNotFoundException.class).when(orderService).delete(NOT_EXIST_ID);

    mockMvc.perform(delete("/orders/" + ORDER.getOrderId())).andDo(print())
        .andExpect(status().isAccepted());
    mockMvc.perform(delete("/orders/" + NOT_EXIST_ID)).andDo(print())
        .andExpect(status().isNotFound());

    verify(orderService).delete(ORDER.getOrderId());
    verify(orderService).delete(NOT_EXIST_ID);
  }

  @Test
  void testGetAllOrders() throws Exception {
    List<Order> ordersList = List.of(ORDER);
    when(orderService.getAll()).thenReturn(ordersList);
    mockMvc.perform(get("/orders")).andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").exists())
        .andExpect(jsonPath("$[*].orderId").isNotEmpty())
        .andExpect(jsonPath("$[*].clientName").isNotEmpty())
        .andExpect(jsonPath("$[*].address").isNotEmpty())
        .andExpect(content().json(ORDERS_ARRAY_JSON));
    verify(orderService).getAll();
  }

  @Test
  void testGetOrder() throws Exception {
    when(orderService.getById(ORDER.getOrderId())).thenReturn(Optional.of(ORDER));
    doThrow(OrderNotFoundException.class).when(orderService).getById(NOT_EXIST_ID);

    mockMvc.perform(get("/orders/" + ORDER.getOrderId())).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$").exists())
        .andExpect(content().json(ORDER_JSON));
    mockMvc.perform(get("/orders/999")).andDo(print()).andExpect(status().isNotFound());

    verify(orderService).getById(ORDER.getOrderId());
    verify(orderService).getById(NOT_EXIST_ID);
  }
}
