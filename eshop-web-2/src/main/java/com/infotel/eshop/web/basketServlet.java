package com.infotel.eshop.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.infotel.eshop.dto.BasketDto;
import com.infotel.eshop.dto.CustomerDto;
import com.infotel.eshop.dto.OrderDto;
import com.infotel.eshop.exception.OrderException;
import com.infotel.eshop.service.OrderService;

@WebServlet("/basket")
public class basketServlet extends HttpServlet{

	private OrderService service; //= new OrderServiceImpl();
	//private BasketDto basket;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		service = ctx.getBean(OrderService.class);
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{


		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/basket.jsp");
		rd.forward(req, resp);


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		HttpSession session = req.getSession();
		BasketDto basket = (BasketDto) session.getAttribute("basket");
		CustomerDto cust = (CustomerDto) session.getAttribute("customer");

		if(basket.getCustomer() == null) {
			if(cust == null) {
				resp.sendRedirect("login");
				return;
			}
		}

		try {
			OrderDto order = service.checkoutBasket(basket);
			req.setAttribute("order", order);

			// basket.clear
			req.getSession().removeAttribute("basket");

			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/order-success.jsp");
			rd.forward(req, resp);
		}

		catch(OrderException e) {
			req.setAttribute("error", "la commande n'est pas passé.");
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/basket.jsp");
			rd.forward(req, resp);
		}
	}
}
