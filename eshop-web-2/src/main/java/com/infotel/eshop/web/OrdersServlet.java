package com.infotel.eshop.web;

import java.io.IOException;
import java.util.List;

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

import com.infotel.eshop.dto.CustomerDto;
import com.infotel.eshop.dto.OrderDto;
import com.infotel.eshop.service.AccountService;
import com.infotel.eshop.service.OrderService;
import com.infotel.eshop.service.OrderServiceImpl;

@WebServlet("/orders")

public class OrdersServlet extends HttpServlet{

	private OrderService service;  //= new OrderServiceImpl();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		service =  ctx.getBean(OrderService.class);
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		// Instancions une session pour les clients

		HttpSession session = req.getSession();
		CustomerDto cust = (CustomerDto) session.getAttribute("customer");//req.getAttribute("customer")	;

		// On recupère les commandes par clients
		List<OrderDto> orders= service.getOrdersByCustomer(cust.getUsername());

		// partie servlet
		req.setAttribute("orders", orders);
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/orders.jsp");
		rd.forward(req, resp);

	}
}