package com.infotel.eshop.dao;

import java.util.List;

import com.infotel.eshop.dto.OrderItemDto;
import com.infotel.eshop.model.OrderItem;

public interface OrderItemDao{
	// On crée les valeurs des commandes de valeurs?
	void create(OrderItem item);
	// On charge les valeurs de la commande à partir des identifiants. 
	List<OrderItem> findManyByByOrder(int orderId);
}
