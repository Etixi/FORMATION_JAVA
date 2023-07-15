package com.infotel.eshop.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
//	@PostConstruct
//	public void init() {
//		System.out.println("****************************");
//	}
	
	@RequestMapping("/hello/{nom}")
	public ModelAndView sayHello(@PathVariable("nom") String leNom, Model model) {
		System.out.println("LE NOM : " + leNom);
		model.addAttribute("nom", leNom);
		return new ModelAndView("hello");
	}

}
