package com.infotel.eshop.mapper;

import com.infotel.eshop.dto.AuthorDto;
import com.infotel.eshop.model.Author;

public interface AuthorMapper {
	
	static AuthorDto authorToAuthorDto(Author a) {
		AuthorDto aDto = new AuthorDto();
		aDto.setId(a.getId());
		aDto.setName(a.getName());
		return aDto;
	}
}
