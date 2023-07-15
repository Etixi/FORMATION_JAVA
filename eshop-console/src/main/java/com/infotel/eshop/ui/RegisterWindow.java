package com.infotel.eshop.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.infotel.eshop.dto.RegisterDto;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.exception.RegisterException;
import com.infotel.eshop.exception.UserAlreadyExistsException;
import com.infotel.eshop.mapper.UserMapper;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.service.AccountService;
import com.infotel.eshop.service.AccountServiceImpl;
import com.infotel.eshop.service.OrderService;

public class RegisterWindow extends Window {


	private AccountService service; //  = new AccountServiceImpl();

	public RegisterWindow() {
		super("Incription");
		
		ApplicationContext ctx = Session.Instance.getAttribute("context");
		this.service = ctx.getBean(AccountService.class);
	}


	@Override
	protected  void renderBody(NavigationRequest request)  {
		render("Veuillez saisir les informations suivantes : ");
		render("Idenfiant");
		String username = readInputText();
		render("Mot de Passe");
		String password =  readInputText();
		render("Prenom");
		String firstName =  readInputText();
		render("Nom");
		String lastName =  readInputText();



		RegisterDto cust = new RegisterDto(username,password, firstName, lastName);
		
		try {
			service.registerCustomer(cust);;
			render();
			render("Vous êtes inscrit");

		} catch(UserAlreadyExistsException e) {
			e.printStackTrace();
			render("Echec de l'inscription : Idenfiant est déjà utilisé");
		}
		catch(RegisterException e) {
			e.printStackTrace();
			render("Echec de l'inscription");
		} catch (EShopException e) {

			e.printStackTrace();
			render("Problème système");
		}
		render(" ");
		navigate("home");

	}



}

