package com.infotel.eshop.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/home")
public class HomeServlet extends HttpServlet{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		rd.forward(req, resp);
//	HttpSession session = req.getSession();
//	CustomerDto cust = (CustomerDto) session.getAttribute("customer");//req.getAttribute("customer")	;
//
//	resp.setContentType("text/html");
//
//
//	PrintWriter pw = resp.getWriter();
//
//	pw.println("<!DOCTYPE html>");
//	pw.println("<html lang=\"en\">");
//	pw.println("<head>");
//	pw.println("<meta charset=\"UTF-8\">");
//	pw.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
//	pw.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
//	pw.println("<title>Document</title>");
//	pw.println("</head>");
//	pw.println("<body>");
//	pw.println("<h1>Accueil</h1>");
//
//	if(cust != null) {
//	pw.println("<p>Bienvenue " + cust.getFirstName() + " " + cust.getLastName() + "</p>");
//	}
//
//
//	pw.println("</body>");
//	pw.println("</html>");
//
//	}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		doGet(req, resp);
	}
}
