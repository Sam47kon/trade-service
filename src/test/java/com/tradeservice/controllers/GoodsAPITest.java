package com.tradeservice.controllers;

import com.tradeservice.entities.Goods;
import com.tradeservice.services.impl.GoodsServiceImpl;
import com.tradeservice.services.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest
class GoodsAPITest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GoodsServiceImpl goodsService;

  // TODO без этого errors  UnsatisfiedDependencyException: Error creating bean with name 'orderAPI'  defined in file
  @MockBean
  private OrderServiceImpl orderService;

  @BeforeEach
  void setUp() {
    Goods goods1 = new Goods(1L, "ТОВАР 1", 11D);
    Goods goods2 = new Goods(2L, "ТОВАР 2", 22D);
    Goods goods3 = new Goods(3L, "ТОВАР 3", 33D);
    Goods goods4 = new Goods(4L, "ТОВАР 4", 44D);
  }


  //
  @Test
  void addGoods() throws Exception {
    Goods goods1 = new Goods(1L, "ТОВАР 1", 11D);

//    BDDMockito.given(goodsService.add(goods1)).willReturn(goods1);

    this.mockMvc.perform(MockMvcRequestBuilders.post("/goods", goods1))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.content().
            json("{'goodsId': 1, 'name': 'ТОВАР 1', 'price': 11}"));
  }

  @Test
  void editGoods() {
  }

  @Test
  void deleteGoods() {
  }

  @Test
  void getAllGoods() {
  }

  @Test
  void getGoodsById() {
  }
}
