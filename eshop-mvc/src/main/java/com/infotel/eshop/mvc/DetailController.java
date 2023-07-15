package com.infotel.eshop.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.infotel.eshop.dto.BookFullDto;
import com.infotel.eshop.service.CatalogService;

@Controller
@SessionAttributes("book")
public class DetailController {
	
	@Autowired
	private CatalogService service;
	
	
	
	@GetMapping  @RequestMapping("/detail")
	public String DetailGet
	
	(
	@RequestParam(name="id", required=false) Integer bookId,
	 Model model
	 
			) {
		if(bookId != null) {
		BookFullDto book = service.loadBookDetails(bookId);
		model.addAttribute("book", book);
		
		}
		return "detail";
		
	}
	
	
	
	
	
}
	
	
	
	
	
	
	
	


