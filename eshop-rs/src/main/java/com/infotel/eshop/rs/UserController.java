package com.infotel.eshop.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotel.eshop.dto.CustomerDto;
import com.infotel.eshop.dto.LoginDto;
import com.infotel.eshop.exception.AuthException;
import com.infotel.eshop.service.AccountService;

@RestController @RequestMapping(
		
		path = "/users", consumes= MediaType.APPLICATION_JSON_VALUE,
				  produces= MediaType.APPLICATION_JSON_VALUE
		
		)
public class UserController {
	
	@Autowired
	private AccountService service;
	
	
	@PostMapping("/login")
	public ResponseEntity<CustomerDto> login(@RequestBody LoginDto login) throws AuthException {
		try {
			CustomerDto cust = service.authentauthenficateCustomer(login);
			return ResponseEntity.ok(cust);
		} catch (AuthException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}	
	}
}


