package com.tradeservice.TestProject.service;

import com.tradeservice.TestProject.Service.GoodsService;
import com.tradeservice.TestProject.config.TestDataBaseConfig;
import com.tradeservice.TestProject.util.GoodsUtil;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@DirtiesContext
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestDataBaseConfig.class})
@WebAppConfiguration
public class GoodsServiceTest { // TODO FIXME PLS!!!

  @Resource
  private EntityManagerFactory emf;
  private EntityManager em;

  @Resource
  private GoodsService goodsService;

  @BeforeEach
  public void beforeEach() {
    em = emf.createEntityManager();
  }

  @Test
  public void testCreateGoods() {
    goodsService.addGoods(GoodsUtil.createGoods());
  }
}
