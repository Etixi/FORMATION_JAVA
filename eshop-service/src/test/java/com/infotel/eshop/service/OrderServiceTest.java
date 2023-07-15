package com.infotel.eshop.service;

import static com.infotel.eshop.test.TestUtils.executeScript;

import static com.infotel.eshop.test.TestUtils.getDbSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.assertj.core.util.Arrays;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.infotel.eshop.config.TestConfig;
import com.infotel.eshop.dto.BasketDto;
import com.infotel.eshop.dto.BookLightDto;
import com.infotel.eshop.dto.CustomerDto;
import com.infotel.eshop.dto.OrderDto;
import com.infotel.eshop.dto.OrderItemDto;
import com.infotel.eshop.exception.OrderException;

public class OrderServiceTest {
	
	OrderService service;
	
	@BeforeEach
	void init() throws Exception {
		//service = new OrderServiceImpl();
		
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context-test.xml");
		
		//service = ctx.getBean(OrderService.class);
		ApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfig.class);
		
		executeScript("src/test/resources/scripts/clean-db-sql.sql");
	}
	
	@Nested
	class it_should_create_an_order{
		
		@Test
		void order_with_two_lines() throws OrderException {
			
			//OrderService service = new OrderServiceImpl();
			
			BookLightDto book1 = new BookLightDto();
			book1.setId(1);
			BookLightDto book2 = new BookLightDto();
			book2.setId(2);
			
			CustomerDto cust = new CustomerDto();
			cust.setUsername("emile@mailo.fr");
			
			BasketDto basket = new BasketDto();
			basket.setCustomer(cust);
			basket.addItem(book1, 1);
			basket.addItem(book2, 2);
			
			
			
			
			OrderDto order = service.checkoutBasket(basket);
			
			assertThat(order).isNotNull()
						.extracting("number", "status", "customer.username")
						.containsExactly("CDE23-0011", "Allocated", "emile@mailo.fr");
			
			assertThat(order.getId()).isGreaterThan(14);
			assertThat(order.getCreated()).isBefore(LocalDateTime.now().plusSeconds(1));
			assertThat(order.getCreated()).isAfter(LocalDateTime.now().minusSeconds(10));
			
			assertThat(order.getItems()).isNotNull().hasSize(2).doesNotContainNull();
			
			OrderItemDto item1 = order.getItems().get(0);
			assertThat(item1).extracting("quantity", "unitPrice", "book.id", "book.title")
							.containsExactly(1, 9.99, 1, "Astérix le Gaulois");
			
			OrderItemDto item2 = order.getItems().get(1);
			assertThat(item2).extracting("quantity", "unitPrice", "book.id", "book.title")
							.containsExactly(2, 9.99, 2, "La serpe d'or");
			
			
			// Assert table in db
			
			Table table = new Table(getDbSource(), "purchase_order",
					Arrays.array(Table.Order.asc("id")));
			
			assertThat(table).hasNumberOfRows(15)
							.row(14)
							.value("id").isEqualTo(order.getId())
							.value("order_number").isEqualTo("CDE23-0011")
							.value("created").isEqualTo(order.getCreated())
							.value("status").isEqualTo("Allocated")
							.value("customer_id").isEqualTo("emile@mailo.fr");
							
			
			Table itemCounterTable = new Table(getDbSource(), "item_counter",
					Arrays.array(Table.Order.asc("id")));
			//1, 0, 2, 12.99, 4, 1
			assertThat(itemCounterTable ).hasNumberOfRows(2)
								.row(1)
								.value("item").isEqualTo("ORDER-23")
								.value("item_value").isEqualTo(11);
								
			
			Table table1 = new Table(getDbSource(), "order_item",
					Arrays.array(Table.Order.asc("id")));
			//1, 0, 2, 12.99, 4, 1
			assertThat(table1).hasNumberOfRows(21)
			.row(19)
			.value("id").isEqualTo(item1.getId())
			.value("position").isEqualTo(0)
			.value("quantity").isEqualTo(1)
			.value("unit_price").isEqualTo(9.99)
			.value("book_id").isEqualTo(1)
			.value("order_id").isEqualTo(order.getId())
			
			.row(20)
			.value("id").isEqualTo(item2.getId())
			.value("position").isEqualTo(0)
			.value("quantity").isEqualTo(2)
			.value("unit_price").isEqualTo(9.99)
			.value("book_id").isEqualTo(2)
			.value("order_id").isEqualTo(order.getId());
			
			
			
			
			
		}
	}
	

}
