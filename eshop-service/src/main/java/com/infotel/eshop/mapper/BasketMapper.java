package com.infotel.eshop.mapper;

import java.util.List;

import com.infotel.eshop.dto.BasketDto;
import com.infotel.eshop.model.Basket;
import com.infotel.eshop.model.BasketItem;

public interface BasketMapper {
	
	 static Basket basketDtoToBasket(BasketDto dto) {
	        Basket basket = new Basket();

	        List<BasketItem> items = dto.getItems()
	                                    .stream()
	                                    .map(BasketItemMapper::basketItemDtoToBasketItem)
	                                    .toList();
	        basket.setItems(items);

	        return basket;
	    }
}
