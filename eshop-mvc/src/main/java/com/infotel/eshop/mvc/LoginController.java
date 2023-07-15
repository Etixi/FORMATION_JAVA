package com.infotel.eshop.mvc;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.infotel.eshop.dto.CustomerDto;
import com.infotel.eshop.dto.LoginDto;
import com.infotel.eshop.exception.AuthException;
import com.infotel.eshop.service.AccountService;

@Controller 
@RequestMapping("/login")
@SessionAttributes("customer")
public class LoginController {
	
	@Autowired
	private AccountService service;
	
	@ModelAttribute("loginForm")
	public LoginForm setupForm() {
		 return new LoginForm();
		}

	
	
	@GetMapping
	public String showForm() {
		return "login";
	}
	
	@PostMapping
	public String login(
			@ModelAttribute("loginForm") 			
			@Valid LoginForm form, 
			BindingResult result, 
			Model model
						
			) {
		System.out.println("Demande d'authentification : " + form);
		
		if(result.hasErrors()) {
			return "login";
		}
		
		LoginDto dto = new LoginDto();
		dto.setUsername(form.getUsername());
		dto.setPassword(form.getPassword());
		
		try {
			CustomerDto cust = service.authentauthenficateCustomer(dto);
			model.addAttribute("customer", cust);
			return "redirect:home";
			
		} catch (AuthException e) {
			model.addAttribute("error", "true");
			return "login";
		}
		
		
	}
	
}
