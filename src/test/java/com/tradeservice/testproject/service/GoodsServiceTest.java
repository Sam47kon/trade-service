//package com.tradeservice.testproject.service;
//
//import com.tradeservice.testproject.services.GoodsService;
//import com.tradeservice.testproject.config.TestDataBaseConfig;
//import com.tradeservice.testproject.util.GoodsUtil;
//import javax.annotation.Resource;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@DirtiesContext
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {TestDataBaseConfig.class})
////@WebAppConfiguration
//public class GoodsServiceTest { // TODO FIXME PLS!!!
//
//  @Resource
//  private EntityManagerFactory emf;
//  private EntityManager em;
//
//  @Resource
//  private GoodsService goodsService;
//
//  @BeforeEach
//  public void beforeEach() {
//    em = emf.createEntityManager();
//  }
//
//  @Test
//  public void testCreateGoods() {
//    goodsService.add(GoodsUtil.createGoods());
//  }
//}
