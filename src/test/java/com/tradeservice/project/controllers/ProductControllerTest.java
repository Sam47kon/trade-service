package com.tradeservice.project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradeservice.project.ecxeptions.ProductNotFoundException;
import com.tradeservice.project.service.impl.ProductServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.tradeservice.project.util.TestConstants.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl productService;

    @SneakyThrows
    private static String asJsonString(final Object obj) {
        return new ObjectMapper().writeValueAsString(obj);
    }

    @AfterEach
    void after() {
        verifyNoMoreInteractions(productService);
    }

    @Test
    void testCreateProduct() throws Exception {
        when(productService.add(PRODUCT_1)).thenReturn(PRODUCT_1);

        mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(PRODUCT_1)).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.name").value(PRODUCT_1.getName()))
                .andExpect(jsonPath("$.price").value(PRODUCT_1.getPrice()));

        verify(productService).add(PRODUCT_1);
    }

    @Test
    void testUpdateProduct() throws Exception {
        when(productService.update(PRODUCT_1_TO_UPDATE, PRODUCT_1.getProductId()))
                .thenReturn(PRODUCT_1_TO_UPDATE);

        mockMvc.perform(put("/products/" + PRODUCT_1.getProductId())
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(PRODUCT_1_TO_UPDATE)))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.productId").value(PRODUCT_1.getProductId()))
                .andExpect(jsonPath("$.name").value(PRODUCT_1_TO_UPDATE.getName()))
                .andExpect(jsonPath("$.price").value(PRODUCT_1_TO_UPDATE.getPrice()));

        verify(productService).update(PRODUCT_1_TO_UPDATE, PRODUCT_1.getProductId());
    }

    @Test
    void testUpdateProduct_fail_not_found() throws Exception {
        when(productService.update(PRODUCT_1_TO_UPDATE, NOT_EXIST_ID))
                .thenThrow(ProductNotFoundException.class);

        mockMvc.perform(put("/products/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(PRODUCT_1_TO_UPDATE)))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(productService).update(PRODUCT_1_TO_UPDATE, NOT_EXIST_ID);
    }

    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(productService).delete(PRODUCT_1.getProductId());
        doNothing().when(productService).delete(PRODUCT_2.getProductId());
        doThrow(ProductNotFoundException.class).when(productService).delete(NOT_EXIST_ID);

        mockMvc.perform(delete("/products/" + PRODUCT_1.getProductId())).andDo(print())
                .andExpect(status().isAccepted());
        mockMvc.perform(delete("/products/" + PRODUCT_2.getProductId())).andDo(print())
                .andExpect(status().isAccepted());
        mockMvc.perform(delete("/products/" + NOT_EXIST_ID)).andDo(print())
                .andExpect(status().isNotFound());

        mockMvc.perform(delete("/products/" + NOT_EXIST_ID))
                .andExpect(status().isNotFound());

        verify(productService).delete(PRODUCT_1.getProductId());
        verify(productService).delete(PRODUCT_2.getProductId());
        verify(productService, times(2)).delete(NOT_EXIST_ID);
    }

    @Test
    void testGetAllProducts() throws Exception {
        when(productService.getAll()).thenReturn(PRODUCT_LIST);
        mockMvc.perform(get("/products/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$[*].productId").isNotEmpty())
                .andExpect(jsonPath("$[*].name").isNotEmpty());
        verify(productService).getAll();
    }

    @Test
    void testGetProduct() throws Exception {
        when(productService.getById(PRODUCT_1.getProductId())).thenReturn(Optional.of(PRODUCT_1));

        mockMvc.perform(get("/products/" + PRODUCT_1.getProductId())).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productId").value((PRODUCT_1.getProductId())))
                .andExpect(jsonPath("$.name", is(PRODUCT_1.getName())))
                .andExpect(jsonPath("$.price", is(PRODUCT_1.getPrice())));

        verify(productService).getById(PRODUCT_1.getProductId());
    }

    @Test
    void testGetProduct_fail_not_found() throws Exception {
        doThrow(ProductNotFoundException.class).when(productService).getById(NOT_EXIST_ID);
        mockMvc.perform(get("/products/999")).andDo(print()).andExpect(status().isNotFound());
        verify(productService).getById(NOT_EXIST_ID);
    }
}
