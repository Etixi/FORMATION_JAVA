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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.infotel.eshop.dto.CustomerDto;
import com.infotel.eshop.dto.LoginDto;
import com.infotel.eshop.exception.AuthException;
import com.infotel.eshop.service.AccountService;
import com.infotel.eshop.service.AccountServiceImpl;
import com.infotel.eshop.service.CatalogService;


@WebServlet(
		urlPatterns = "/login"
		//initParams =
		//	@WebInitParam(name = "greet", value = "Hallo")
		)
public class LoginServlet extends HttpServlet {

	private AccountService service;  //= new AccountServiceImpl();
	private static final Logger log = LogManager.getLogger("LoginServlet.class");
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		service = ctx.getBean(AccountService.class);
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		displayForm(req, resp);

//		resp.setContentType("text/html");
//
//		PrintWriter pw = resp.getWriter();
//
//		pw.println("<!DOCTYPE html>");
//		pw.println("<html lang=\"en\">");
//		pw.println("<head>");
//		pw.println("<meta charset=\"UTF-8\">");
//		pw.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
//		pw.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
//		pw.println("<title>Document</title>");
//		pw.println("</head>");
//		pw.println("<body>");
//		pw.println("<h1>Authentification</h1>");
//		pw.println("<form action=\"login\" method =\"post\">");
//		pw.println("<label for =\"usernameInput\">Identifiant</label>");
//		pw.println("<input id=\"usernameInput\" type=\"text\" name=\"username\">");
//		pw.println("<label for =\"passwordInput\">Mot de Passe</label>");
//		pw.println("<input id=\"passwordInput\" type=\"password\" name=\"password\">");
//		pw.println("<button type=\"submit\">Se connecter</button>");
//		pw.println("</form>");
//
//		pw.println("<div style = \"color : red;\">Echec de l'authentification</div>");
//
//		pw.println("</body>");
//		pw.println("</html>");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{



		try {
			String username = req.getParameter("username");
			String password = req.getParameter("password");

			if(log.isDebugEnabled()) {
				log.debug("demande authentification" +  username + " " +  password.replaceAll(".", "*"));

			}


			LoginDto login = new LoginDto();
			login.setUsername(username);
			login.setPassword(password);
			CustomerDto cust = service.authentauthenficateCustomer(login);

			HttpSession session = req.getSession();
			session.setAttribute("customer", cust);
			req.setAttribute("customer", cust);
			//resp.setContentType("text/html");
			//PrintWriter pw = resp.getWriter();


			// Equivalent du navigate de EshopConsole en mode servlet
			//RequestDispatcher rd = req.getRequestDispatcher("/home");
			//rd.forward(req, resp);

			resp.sendRedirect("home");

//			pw.println("<!DOCTYPE html>");
//			pw.println("<html lang=\"en\">");
//			pw.println("<head>");
//			pw.println("<meta charset=\"UTF-8\">");
//			pw.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
//			pw.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
//			pw.println("<title>Document</title>");
//			pw.println("</head>");
//			pw.println("<body>");
//			pw.println("<h1>Accueil</h1>");
//			pw.println("<p>Bienvenue " + cust.getFirstName() + " " + cust.getLastName() + "</p>");
//			pw.println("</form>");
//
//			pw.println("</body>");
//			pw.println("</html>");

		} catch (AuthException e) {
			log.warn("erreur login", e);
			req.setAttribute("error", "login error");
			displayForm(req, resp);


//			doGet(req, resp);
//			resp.setContentType("text/html");
//			PrintWriter pw = resp.getWriter();
//
//			pw.println("<!DOCTYPE html>");
//			pw.println("<html lang=\"en\">");
//			pw.println("<head>");
//			pw.println("<meta charset=\"UTF-8\">");
//			pw.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
//			pw.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
//			pw.println("<title>Document</title>");
//			pw.println("</head>");
//			pw.println("<body>");
//			pw.println("<h1>Authentification</h1>");
//			pw.println("<form action=\"login\" method =\"post\">");
//			pw.println("<label for =\"usernameInput\">Identifiant</label>");
//			pw.println("<input id=\"usernameInput\" type=\"text\" name=\"username\">");
//			pw.println("<label for =\"passwordInput\">Mot de Passe</label>");
//			pw.println("<input id=\"passwordInput\" type=\"password\" name=\"password\">");
//			pw.println("<button type=\"submit\">Se connecter</button>");
//			pw.println("</form>");
//
//			pw.println("<div style = \"color : red;\">Echec de l'authentification</div>");
//			pw.println("</body>");
//			pw.println("</html>");

			//e.printStackTrace();
		}



	}

	private void displayForm(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		rd.forward(req, resp);

//		resp.setContentType("text/html");
//
//		PrintWriter pw = resp.getWriter();
//
//		pw.println("<!DOCTYPE html>");
//		pw.println("<html lang=\"en\">");
//		pw.println("<head>");
//		pw.println("<meta charset=\"UTF-8\">");
//		pw.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
//		pw.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
//		pw.println("<title>Document</title>");
//		pw.println("</head>");
//		pw.println("<body>");
//		pw.println("<h1>Authentification</h1>");
//		pw.println("<form action=\"login\" method =\"post\">");
//		pw.println("<label for =\"usernameInput\">Identifiant</label>");
//		pw.println("<input id=\"usernameInput\" type=\"text\" name=\"username\">");
//		pw.println("<label for =\"passwordInput\">Mot de Passe</label>");
//		pw.println("<input id=\"passwordInput\" type=\"password\" name=\"password\">");
//		pw.println("<button type=\"submit\">Se connecter</button>");
//		pw.println("</form>");
//
//		if (req.getAttribute("error") != null) {
//
//			pw.println("<div style = \"color : red;\">Echec de l'authentification</div>");
//		}
//
//		pw.println("</body>");
//		pw.println("</html>");

	}
}
