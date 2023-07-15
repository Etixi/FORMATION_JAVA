package com.infotel.eshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.infotel.eshop.dto.BookFullDto;
import com.infotel.eshop.dto.BookLightDto;
import com.infotel.eshop.dto.CategoryDto;
import com.infotel.eshop.dto.ImageDataDto;

@Service
public interface CatalogService {

	BookFullDto loadBookDetails(int id);

	List<BookLightDto> searchBooks(String keyword, int categoryId);

	List<CategoryDto> loadCategories();
	
	ImageDataDto loadImage(int imageid);
	
	Integer getImageIdByBook(int bookId);
	
	
	

}