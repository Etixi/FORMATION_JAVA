package com.infotel.eshop.mapper;

import com.infotel.eshop.dto.BasketItemDto;
import com.infotel.eshop.model.BasketItem;

public interface BasketItemMapper {
	
	   static BasketItem basketItemDtoToBasketItem(BasketItemDto dto) {
	        BasketItem item = new BasketItem();
	        item.setQuantity(dto.getQuantity());
	        item.setBook(BookMapper.bookLightDtoToBook(dto.getBook()));

	        return item;
	    }
}
