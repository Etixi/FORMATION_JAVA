package com.infotel.eshop.dao;

import java.util.List;

import com.infotel.eshop.dto.OrderDto;
import com.infotel.eshop.model.Order;
import com.infotel.eshop.model.OrderStatus;

public interface OrderDao {
	//On crée un numéro de commande
	void create(Order order);
	// On charge les commandes par client
	List<Order> findManyByCustomer(String username);

	default List<Order> findManyByStatus(OrderStatus... statusList) {
		return List.of();
	}
}
