

package com.infotel.eshop.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.infotel.eshop.dao.UserDao;
import com.infotel.eshop.dto.CustomerDto;
import com.infotel.eshop.dto.LoginDto;
import com.infotel.eshop.dto.MerchantDto;
import com.infotel.eshop.dto.RegisterDto;
import com.infotel.eshop.exception.AuthException;
import com.infotel.eshop.exception.RegisterException;
import com.infotel.eshop.exception.UserAlreadyExistsException;
import com.infotel.eshop.mapper.UserMapper;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.Merchant;
import com.infotel.eshop.model.User;


@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired 
	private UserDao userDao; //= new UserDaoJpa();
	
	
//	public void setUserDao(UserDao userDao) {
//		this.userDao = userDao;
//	}
	
	//CustomerDto authentauthenficateCustomer(LoginDto login)
	//registerCustomer(RegisterDto cust)

	private User authenticate(String username, String password) throws AuthException {
		User user = userDao.findOneByUsername(username);

		if(user !=null && password.equals(user.getPassword())) {
			return user;
		}
		//return null;
		throw new AuthException("Echec de l'authentification");

	}

	
	@Override
	public CustomerDto authentauthenficateCustomer(LoginDto login) throws AuthException {
		User user = authenticate(login.getUsername(), login.getPassword());

		if (user instanceof Customer cust) {
			//cust = (Customer)user;
			return UserMapper.customerToCustomerDto(cust);
		}
		throw new AuthException("Echec de l'authentification : l'utilisateur n'est pas un client");

	}
	
	@Override
	public MerchantDto authentauthenficateMerchant(LoginDto login) throws AuthException {
	

		if (login == null) throw new AuthException("Echec de l'authentification : l'utilisateur n'est pas un client");
		
		User user = authenticate(login.getUsername(), login.getPassword());

		if(user instanceof Merchant merchant) {
			return UserMapper.MerchantToMerchantDto(merchant);
		}
		
		throw new AuthException("Echec de l'authentification : l'utilisateur n'est pas un client");
	}


	
	@Override
	public void registerCustomer(RegisterDto register) throws  RegisterException, UserAlreadyExistsException {
		//
		Customer cust = UserMapper.registerDtoToCustomer(register);
		checkcustomerBeforeRegister(cust);

		User user = userDao.findOneByUsername(cust.getUsername());
		if(user != null) {
			throw new UserAlreadyExistsException("Identifiant déjà utilisé");
		}

		userDao.create(cust);

	}
	

	private void checkcustomerBeforeRegister(Customer cust) throws RegisterException {
		if (cust == null) {
			throw new RegisterException("Objet client null");
		}
		else if (cust.getUsername() == null || cust.getUsername().isBlank()) {
			throw new RegisterException("Identifiant Incorrect");
		}
		else if (cust.getPassword() == null || cust.getPassword().isBlank()) {
			throw new RegisterException("Mot de passe  Incorrect");
		}
		
		//validate identifiant est une addresse mail
		Pattern pattern = Pattern.compile("^(.+)@(.+)\\.(.+)$");
		Matcher matcher = pattern.matcher(cust.getUsername());
		if(!matcher.matches()) {
			throw new RegisterException("Identifiant incorrect: n'est pas une addresse mail.");
		}
	}



}


