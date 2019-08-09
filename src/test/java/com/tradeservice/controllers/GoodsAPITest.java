package com.tradeservice.controllers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradeservice.entities.Goods;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// TODO сделать отдельный конфиг с бобами

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
// TODO ???  в чем отличия с ним и без него или с @RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class GoodsAPITest {

  @Autowired
  private MockMvc mockMvc;


  /*
   * converts a Java object into JSON representation
   */
  static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void editGoods() {
  }

  @Test
  void deleteGoods() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
        .delete("/goods/1"))
        .andExpect(status().isOk()); // TODO здесь поменять на ексепшн, так как привязка к ордер
  }

  @Test
  void addGoods() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders
        .post("/goods")
        .content(asJsonString(new Goods("GOOOOODS", 100500D)))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.goodsId").exists())
        .andExpect(jsonPath("$.name").value("GOOOOODS"));
  }

  @Test
  void getAllGoods() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/goods")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").exists())
        .andExpect(jsonPath("$[*].goodsId").isNotEmpty());
  }

  @Test
  void getGoodsById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/goods/1")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.goodsId", is(1)));
  }
}
