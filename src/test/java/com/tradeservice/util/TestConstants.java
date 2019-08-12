package com.tradeservice.util;

import com.tradeservice.entity.Order;
import com.tradeservice.entity.OrderItem;
import com.tradeservice.entity.Product;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public final class TestConstants {

  public static final Long NOT_EXIST_ID = 999L;
  public static final Product PRODUCT_1 = new Product(1L, "Product #1", 11D);
  public static final Product PRODUCT_1_TO_UPDATE = new Product(1L, "Product #1 updated", 22D);
  public static final Product PRODUCT_2 = new Product(2L, "Product #2", 22D);
  public static final Product PRODUCT_3 = new Product(3L, "Product #3", 33D);
  public static final List<Product> PRODUCT_LIST = List.of(PRODUCT_1, PRODUCT_2, PRODUCT_3);
  public static final String CLIENT_NAME = "Alphabet inc.";

  public static final String ORDERS_ARRAY_JSON = "[{\"orderId\":1,\"" +
      "clientName\":\"Alphabet inc.\"," +
      "\"date\":\"2020-05-03T04:01:03\"," +
      "\"address\":\"Los Angeles, California 90002\"," +
      "\"orderItems\":" +
      "[{\"id\":null,\"product\":{\"productId\":2,\"name\":\"Product #2\",\"price\":22.0}," +
      "\"count\":116},{\"id\":null,\"product\":{\"productId\":1,\"name\":\"Product #1\"," +
      "\"price\":11.0},\"count\":115}]}]\n";

  public static final String ORDER_JSON = "{\"orderId\":1,\"" +
      "clientName\":\"Alphabet inc.\"," +
      "\"date\":\"2020-05-03T04:01:03\"," +
      "\"address\":\"Los Angeles, California 90002\"," +
      "\"orderItems\":" +
      "[{\"id\":null,\"product\":{\"productId\":2,\"name\":\"Product #2\",\"price\":22.0}," +
      "\"count\":116},{\"id\":null,\"product\":{\"productId\":1,\"name\":\"Product #1\"," +
      "\"price\":11.0},\"count\":115}]}\n";

  public static final String ORDER_UPDATED_JSON = "{\"orderId\":1,\"" +
      "clientName\":\"Changed name\"," +
      "\"date\":\"2030-01-01T00:00:00\"," +
      "\"address\":\"Changed address\"," +
      "\"orderItems\":" +
      "[{\"id\":null,\"product\":{\"productId\":1,\"name\":\"Product #1\"," +
      "\"price\":11.0},\"count\":10}]}\n";

  public static final Order ORDER = new Order(1L, CLIENT_NAME,
      LocalDateTime.of(2020, 5, 3, 4, 1, 3),
      "Los Angeles, California 90002", Set
      .of(new OrderItem(PRODUCT_1, 115), new OrderItem(PRODUCT_2, 116)));

  public static final Order ORDER_TO_UPDATE = new Order(1L, "Changed name",
      LocalDateTime.of(2030, 1, 1, 0, 0, 0),
      "Changed address", Set.of(new OrderItem(PRODUCT_1, 10)));
}

