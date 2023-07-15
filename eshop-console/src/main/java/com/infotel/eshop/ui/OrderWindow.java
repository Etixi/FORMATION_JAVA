package com.infotel.eshop.ui;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.infotel.eshop.dto.CustomerDto;
import com.infotel.eshop.dto.OrderDto;
import com.infotel.eshop.dto.OrderItemDto;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.service.CatalogService;
import com.infotel.eshop.service.OrderService;
import com.infotel.eshop.service.OrderServiceImpl;



public class OrderWindow extends Window{
	
	private OrderService service; // = new OrderServiceImpl();

	public OrderWindow() {	
		super("Mes commandes");
		
		ApplicationContext ctx = Session.Instance.getAttribute("context");
		this.service = ctx.getBean(OrderService.class);
	}


	@Override
	protected void renderBody(NavigationRequest request) throws EShopException {
		renderOrders();
		renderMenu();
		
	
}


	private void renderOrders() {
		CustomerDto cust= Session.Instance.getAttribute("customer");
		List<OrderDto> orders= service.getOrdersByCustomer(cust.getUsername());
		
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
			
		if(orders.isEmpty()) {
			render("Vous n'avez pas passez la commande dabs votre historique");
		}
		else {
		
			
			
			//orders.sort(new Comparator<Order>( ) {
				
				//@Override
				//public int compare
				
				//orders.sort((o1, o2) -> o2.getCreated().compareTo(o1.getCreated()));
			orders.sort(this::compareOrders);
			//orders.sort((o1, o2) -> o2.getCreated().compareTo(o1.getCreated()));
					/*int result = o1.getCreated().compareTo(o2.getCreated());
					if(result == 0) {
						result = o1.getId()- o2.getId();
					}
					return result;
				}*/
				
			
			
			for(OrderDto order : orders) {
				render("* " + order.getNumber() + " - " + sdf.format(order.getCreated()));
				for(OrderItemDto item : order.getItems()) {
					String line = String.format(" - %-25s %2s %5.2f €",
							item.getBook().getTitle(), item.getQuantity(), item.getUnitPrice());
					render(line);
					
					
					/*
					 * render(" - " + item.getBook().getTitle() + " - " + item.getUnitPrice() +
					 * " - " + item.getQuantity());
					 */
				}
				render(String.format("%23s Montant : %-5.2f €", "", order.getTotalAmount()));
				render();
			}
		}
	}
	private int compareOrders(OrderDto o1, OrderDto o2) {
		return o2.getCreated().compareTo(o1.getCreated());
	}
	private void renderMenu() {
		render();
		render("1.Retour");

		render();
		render("Votre choix?");
		String choice = readInputText();

		switch(choice) {
		case "1" -> navigate("home");
		}
	}
}
