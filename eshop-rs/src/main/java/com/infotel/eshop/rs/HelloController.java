package com.infotel.eshop.rs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/hello")
public class HelloController {
	
	@GetMapping
	public String sayHello(@RequestParam("name") String name) {
		return "Bonjour " + name  + "!";		
	}

}
