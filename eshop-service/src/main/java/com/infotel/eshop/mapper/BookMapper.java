package com.infotel.eshop.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.infotel.eshop.dto.AuthorDto;
import com.infotel.eshop.dto.BookFullDto;
import com.infotel.eshop.dto.BookLightDto;
import com.infotel.eshop.dto.CategoryDto;
import com.infotel.eshop.model.Author;
import com.infotel.eshop.model.Book;
import com.infotel.eshop.model.BookPrice;
import com.infotel.eshop.model.Category;

public interface BookMapper {

static BookFullDto bookToBookFullDto(Book book, Integer imageId) {
		
		
		
		BookFullDto dto = new BookFullDto();
		
		dto.setId(book.getId());
		dto.setTitle(book.getTitle());
		dto.setPrice(book.getPrice().getValue());
		dto.setIsbn(book.getDetails().getIsbn());
		dto.setRelease(book.getDetails().getRelease());
		dto.setOverview(book.getDetails().getOverview());
		dto.setTags(book.getTags().stream().toList());
		
		Category category = book.getCategory();
		CategoryDto catDto = CategoryMapper.categoryToCategoryDto(category);
		dto.setCategory(catDto);
		
		List<AuthorDto> authorsDto = 
				
				book.getAuthors().stream()
				.map(a-> AuthorMapper.authorToAuthorDto(a))
				.collect(Collectors.toList());
		dto.setAuthors(authorsDto);
		
		
		dto.setImageId(imageId);
		return dto;
	}

static AuthorDto authorToAuthorDto(Author a) {
	AuthorDto aDto = new AuthorDto();
	aDto.setId(a.getId());
	aDto.setName(a.getName());
	return aDto;
}

static CategoryDto categoryToCategoryDto(Category category) {
	CategoryDto catDto = new CategoryDto();
	catDto.setId(category.getId());
	catDto.setName(category.getName());
	return catDto;
}

static BookLightDto bookToBookLightDto(Book book, int imageId) {
    BookLightDto dto = new BookLightDto();
    dto.setId(book.getId());
    dto.setTitle(book.getTitle());
    if (book.getPrice() != null) {
        dto.setPrice(book.getPrice().getValue());
    }
    dto.setImageId(imageId);

    return dto;
}

static Book bookLightDtoToBook(BookLightDto dto) {
    Book book = new Book();
    book.setId(dto.getId());

    BookPrice price = new BookPrice();
    price.setValue(dto.getPrice());
    book.setPrice(price);

    return book;
}
}
