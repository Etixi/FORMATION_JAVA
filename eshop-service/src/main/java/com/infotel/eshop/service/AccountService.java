package com.infotel.eshop.service;


import org.springframework.stereotype.Service;

import com.infotel.eshop.dto.CustomerDto;
import com.infotel.eshop.dto.LoginDto;
import com.infotel.eshop.dto.MerchantDto;
import com.infotel.eshop.dto.RegisterDto;
import com.infotel.eshop.exception.AuthException;
import com.infotel.eshop.exception.RegisterException;
import com.infotel.eshop.exception.UserAlreadyExistsException;

@Service("accountService")
public interface AccountService {
	
	//User authenticate(String username, String password)throws AuthException;
	CustomerDto authentauthenficateCustomer(LoginDto login)throws AuthException;
	MerchantDto authentauthenficateMerchant(LoginDto login)throws AuthException;
	void registerCustomer(RegisterDto cust) throws  UserAlreadyExistsException,RegisterException;

}
