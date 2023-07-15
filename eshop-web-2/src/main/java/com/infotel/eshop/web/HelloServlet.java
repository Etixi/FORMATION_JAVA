package com.infotel.eshop.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebServlet(
		urlPatterns = "/hello",
		initParams =
			@WebInitParam(name = "greet", value = "Hallo")
		)
public class HelloServlet extends HttpServlet{

	private final static Logger log = LogManager.getLogger(HelloServlet.class);
	private String greeting;
	private String app;


	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		log.info("Hello Servlet démarre");
		greeting = getInitParameter("greet");
		app = getServletContext().getInitParameter("appName");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				log.info(cookie.getName() + "=> " + cookie.getValue());

			}
		} else {
			Cookie cookie = new Cookie("fruit", "pomme");
			resp.addCookie(cookie);
		}

		//HttpSession = req.getSession();



		String leNom = req.getParameter("nom");
		String lePrenom = req.getParameter("prenom");

		resp.setContentType("text/html");



		PrintWriter pw = resp.getWriter();
		pw.println("<!DOCTYPE html>");
		pw.println("<html lang=\"en\">");
		pw.println("<head>");
		pw.println("<meta charset=\"UTF-8\">");
		pw.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		pw.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		pw.println("<title>Document</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h1>" + app + "</h1>");
		pw.println("<p>Salut " + greeting + lePrenom +" " + leNom + " :o)</p>");
		pw.println("</body>");
		pw.println("</html>");


	}

	@Override
	public void destroy( ){
		super.destroy();
		log.info("Hello Servlet s'arrête");
	}

}
