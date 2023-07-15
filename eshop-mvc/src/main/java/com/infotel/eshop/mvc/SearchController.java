package com.infotel.eshop.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.infotel.eshop.dto.BookLightDto;
import com.infotel.eshop.dto.CategoryDto;
import com.infotel.eshop.service.CatalogService;

@Controller

public class SearchController {
	
	
	@Autowired
	private CatalogService service;
	
	
	@RequestMapping("/search")
	public String Search(
			@RequestParam(name = "keyword", required=false) String keyword,
			@RequestParam(name="catId", required=false) Integer categoryId,
			 Model model) 
	
	{
		
		List<CategoryDto> categories = service.loadCategories();
		model.addAttribute("categories", categories);
		
		
		if(keyword != null) {
		List<BookLightDto>  books = service.searchBooks(keyword, categoryId);
		model.addAttribute("books", books);
		
		}
		
		
		return "search";
	
	
	}
}
