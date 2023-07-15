package com.infotel.eshop.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.infotel.eshop.dto.BasketDto;
import com.infotel.eshop.dto.BasketItemDto;
import com.infotel.eshop.dto.BookLightDto;
import com.infotel.eshop.dto.CustomerDto;
import com.infotel.eshop.dto.OrderDto;
import com.infotel.eshop.exception.OrderException;
import com.infotel.eshop.service.OrderService;
import com.infotel.eshop.service.OrderServiceImpl;


public class BasketWindow extends Window {

	private OrderService service; // = new OrderServiceImpl();
	private BasketDto basket;


	public BasketWindow() {
		super("Mon panier");
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:app-context-service.xml");
		this.service =  ctx.getBean(OrderService.class);
	}
	

	
	@Override
	protected void renderBody(NavigationRequest request) {

		render("Bienvenue dans mon panier");
		Session session = Session.Instance;//getSession();
		basket = session.getAttribute("basket");

		if(basket == null) {
			renderEmpty();
		}
		else {
			renderContent();

		}
		renderMenu();
	}



	private void renderEmpty() {
		render("Votre panier est vide");

	}

	private void renderContent() {
		render("saisir dans votre panier");
		
		basket.getItems().stream().forEach(c -> renderBasketItem(c));
		//for (BasketItem item: basket.getItems()) {
		//	renderBasketItem(item);
		//}
		String amountLine = String.format("%25s : %-5.2f €", "Montant", basket.getTotalAmount());
		render(amountLine);
	}


	private void renderBasketItem(BasketItemDto item) {
		BookLightDto book = item.getBook();
		String line = String.format("* %-20s %2s  %-5.2f €",
				book.getTitle(), item.getQuantity(), book.getPrice());
		render(line);
	}


	private boolean isBasketEmpty() {
		return basket == null || basket.getItems()== null || basket.getItems().isEmpty();
	}


	private void renderMenu() {
		render();
		if(!isBasketEmpty()) render("1.Passer la commande.");
		render("2.Retour");

		render();
		render("Votre choix?");
		String choice = readInputText();

		switch(choice) {
		case "1" -> {
			if(!isBasketEmpty()) checkout();
		}
		case "2" -> navigate("home");
		}
	}

	

	private void checkout() {
		try {

			Session session = Session.Instance; //getSession();
			CustomerDto cust = session.getAttribute("customer");

			if(cust==null) {
				render("Veuillez-vous connecter");

				render();
				navigate("login", "basket");
			}
			else {
				basket.setCustomer(cust);
				OrderDto order = service.checkoutBasket(basket);
				render("Votre commande est passée : " + order.getNumber());
				
				//Vider le panier
				session.removeAttribute("basket");
				//basket=null;
				//basket.clear();
					
				render();
				navigate("home");
			}


		}catch(OrderException e) {
			render("La commande a échoué");
		}

	}
}
