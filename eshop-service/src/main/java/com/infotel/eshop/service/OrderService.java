package com.infotel.eshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.infotel.eshop.dto.BasketDto;
import com.infotel.eshop.dto.OrderDto;
import com.infotel.eshop.exception.OrderException;
import com.infotel.eshop.model.Order;

@Service
public interface OrderService {
	OrderDto checkoutBasket(BasketDto  basket) throws OrderException;
	List<OrderDto> getOrdersByCustomer(String username);
	List<OrderDto> getOrdersToProcess();
}
