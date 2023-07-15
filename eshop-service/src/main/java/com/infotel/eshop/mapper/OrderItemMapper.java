package com.infotel.eshop.mapper;

import com.infotel.eshop.dto.OrderItemDto;
import com.infotel.eshop.model.OrderItem;

public interface OrderItemMapper {
	
	 static OrderItemDto orderItemToOrderItemDto(OrderItem item) {
	        OrderItemDto dto = new OrderItemDto();
	        dto.setId(item.getId());
	        dto.setQuantity(item.getQuantity());
	        dto.setUnitPrice(item.getUnitPrice());
	        dto.setBook(BookMapper.bookToBookLightDto(item.getBook(), -1));

	        return dto;
	    }
}
