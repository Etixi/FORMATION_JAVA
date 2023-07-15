package com.infotel.eshop.mapper;

import java.util.List;

import com.infotel.eshop.dto.OrderDto;
import com.infotel.eshop.dto.OrderItemDto;
import com.infotel.eshop.model.Order;

public interface OrderMapper {
	
	static OrderDto orderToOrderDto(Order order) {
		
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setNumber(order.getNumber());
        dto.setCreated(order.getCreated());
        dto.setStatus(order.getStatus().name());
        dto.setTotalAmount(order.getTotalAmount());

        dto.setCustomer(UserMapper.customerToCustomerDto(order.getCustomer()));
        List<OrderItemDto> items = order.getItems()
                                        .stream()
                                        //.map(item -> OrderItemMapper.orderItemToOrderItemDto(item))
                                        .map(OrderItemMapper::orderItemToOrderItemDto)
                                        .toList();
        dto.setItems(items);

        return dto;
    }
}
