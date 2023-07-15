package com.infotel.eshop.rs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotel.eshop.dto.BasketDto;
import com.infotel.eshop.dto.OrderDto;
import com.infotel.eshop.exception.OrderException;
import com.infotel.eshop.service.OrderService;

@RestController @RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	// METHODE
	//  ====> OrderDto checkoutBasket(BasketDto  basket)
	//  ===> List<OrderDto> getOrdersByCustomer(String username);
	
	
	@PostMapping("/checkout")
	public ResponseEntity<OrderDto> checkoutBasket(@RequestBody BasketDto basket) {
		try {
			
			// Verification du panier
			OrderDto order = service.checkoutBasket(basket);
			return ResponseEntity.status(HttpStatus.CREATED).body(order);
		} catch (OrderException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/user/{username}")
	public List<OrderDto> getOrdersByCustomer(@PathVariable("username") String username) {
		return service.getOrdersByCustomer(username);
	}
	
	
	
	
	
	
	
	
	
	
	

}
