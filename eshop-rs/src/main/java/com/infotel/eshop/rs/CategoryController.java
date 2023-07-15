package com.infotel.eshop.rs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotel.eshop.dto.CategoryDto;
import com.infotel.eshop.dto.CategoryListDto;
import com.infotel.eshop.service.CatalogService;

@RestController @RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CatalogService service;
	
	@GetMapping(produces= MediaType.APPLICATION_XML_VALUE
		)
	public CategoryListDto getCategoriesXml() {
		List<CategoryDto> categories = service.loadCategories();
		CategoryListDto catList = new CategoryListDto();
		catList.setCategories(categories);
		
		return catList;
	}
	
	@GetMapping( produces= MediaType.APPLICATION_JSON_VALUE)
	public List<CategoryDto> getCategoriesJson() {
		List<CategoryDto> categories = service.loadCategories();
	
		return categories;
	}

}
