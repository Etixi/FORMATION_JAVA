package com.infotel.eshop.ui;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.infotel.eshop.dto.CustomerDto;
import com.infotel.eshop.dto.LoginDto;
import com.infotel.eshop.exception.AuthException;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.mapper.UserMapper;
import com.infotel.eshop.model.Customer;

import com.infotel.eshop.model.User;
import com.infotel.eshop.service.AccountService;
import com.infotel.eshop.service.AccountServiceImpl;


public class LoginWindow extends Window {
	
	private final static Logger log = LogManager.getLogger(LoginWindow.class);
	private AccountService service; //= new AccountServiceImpl();

	public LoginWindow(){
		super("Authentification");
		ApplicationContext ctx = Session.Instance.getAttribute("context");
		this.service =  ctx.getBean(AccountService.class);
		
	}
	
	protected void renderTitle()
	{
		render("===========================");
		render ("----  " + getMessage(title) + "---------");
		render("============================");
		render();
		
	}

	@Override
	protected void renderBody(NavigationRequest request) {
		render(getMessage("login.username") +  " :");
		String username = readInputText();

		render(getMessage("login.password") +  " :");
		String password = readInputText();

		try {
			

			LoginDto login = new LoginDto();
			login.setUsername(username);
			login.setPassword(password);
			CustomerDto cust = service.authentauthenficateCustomer(login);
			
					//service.authentauthenficateCustomer(username, password);
			render();
			render(getMessage("login.welcome", cust.getFirstName(), cust.getLastName()));
			
			Session.Instance
				.setAttribute("customer", cust);
//				.setAttribute("fruit", "pomme")
//				.setAttribute("xxx", "yyy");
			
			

		} catch(AuthException ae) {
			//ae.printStackTrace();
			render();
			render (getMessage("login.failed"));
			log.warn("echec del'authentification" , ae.getMessage());
		}
		
		
		render();
		if(request.getParams().isEmpty()) {
		navigate("home");
		}
		else {
			String target = (String) request.getParams().get(0);
			navigate(target);
		}

	}
}

