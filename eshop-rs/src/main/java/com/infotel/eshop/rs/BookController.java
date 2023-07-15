package com.infotel.eshop.rs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotel.eshop.dto.BookFullDto;
import com.infotel.eshop.dto.BookLightDto;
import com.infotel.eshop.dto.SearcdhDto;
import com.infotel.eshop.service.CatalogService;

@RestController @RequestMapping(
		path = "/books"
		//consumes= MediaType.APPLICATION_JSON_VALUE,
		//produces= MediaType.APPLICATION_JSON_VALUE
		)
public class BookController {
	
	@Autowired
	private CatalogService service;
	
	@GetMapping("/{id}")
	public BookFullDto getBook(@PathVariable("id") int id) {
		return service.loadBookDetails(id);
	}
	@PostMapping("/search") 
	public List<BookLightDto> getBook(@RequestBody SearcdhDto data) {
		return service.searchBooks(data.getKeyword(), data.getCategoryId());
	}

}
