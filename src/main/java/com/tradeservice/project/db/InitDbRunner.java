package com.tradeservice.project.db;

import com.tradeservice.project.entity.Order;
import com.tradeservice.project.entity.OrderItem;
import com.tradeservice.project.entity.Product;
import com.tradeservice.project.service.OrderService;
import com.tradeservice.project.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@Configuration
public class InitDbRunner {

	@Bean
	CommandLineRunner initDataBase(ProductService productService, OrderService orderService) {
		Product newProduct1 = new Product("Товар 1", 59.0);
		Product newProduct2 = new Product("Товар 2", 999.0);
		Order newOrder1 = new Order("Клиент 1", LocalDateTime.now(), "Адрес 1");
		Order newOrder2 = new Order("Клиент 2", LocalDateTime.now(), "Адрес 2");

		Set<OrderItem> orderItemSet1 =
				Set.of(new OrderItem(newProduct1, 11), new OrderItem(newProduct2, 12));
		newOrder1.setOrderItems(orderItemSet1);

		Set<OrderItem> orderItemSet2 =
				Set.of(new OrderItem(newProduct1, 22), new OrderItem(newProduct2, 23));
		newOrder2.setOrderItems(orderItemSet2);

		return args -> {
			log.info("Add new goods1: " + productService.add(newProduct1));
			log.info("Add new goods2: " + productService.add(newProduct2));
			for (int i = 3; i <= 50; i++) {
				log.info("Add new goods" + i + ": " + productService.add(
						new Product("Товар " + i, Math.ceil(100 * (Math.random() * i + 1)))));
			}
			log.info("Add new order1: " + orderService.add(newOrder1));
			log.info("Add new order2: " + orderService.add(newOrder2));
		};
	}
}
