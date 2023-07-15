package com.infotel.eshop.mapper;

import com.infotel.eshop.dto.CategoryDto;
import com.infotel.eshop.model.Category;

public interface CategoryMapper {
	
	static CategoryDto categoryToCategoryDto(Category category) {
		CategoryDto catDto = new CategoryDto();
		catDto.setId(category.getId());
		catDto.setName(category.getName());
		return catDto;
	}
}
