package com.infotel.eshop.web;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.infotel.eshop.dto.BasketDto;
import com.infotel.eshop.dto.BookFullDto;
import com.infotel.eshop.dto.CustomerDto;
import com.infotel.eshop.service.CatalogService;
import com.infotel.eshop.service.CatalogServiceImpl;
import com.infotel.eshop.service.OrderService;


@WebServlet("/detail")

public class DetailServlet extends HttpServlet{

	private final static Logger log = LogManager.getLogger(DetailServlet.class);
	private CatalogService service;  //= new CatalogServiceImpl();
	private DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		service =  ctx.getBean(CatalogService.class);
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{


		int bookId = Integer.parseInt(req.getParameter("id"));

		BookFullDto book = service.loadBookDetails(bookId);

		req.setAttribute("book", book);


		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/detail.jsp");
		rd.forward(req, resp);




		//Integer imageId = service.getImageIdByBook(bookId);


		//if(log.isDebugEnabled()) {
		//	log.debug("Recherche de livres : " + bookId);
		//}

		//Book book = service.loadBookDetails(id);


		//resp.setContentType("text/html");
		//resp.setCharacterEncoding("UTF-8");

		//NumberFormat priceFmt = new DecimalFormat("0.00");
		//PrintWriter pw = resp.getWriter();




//		pw.println("<!DOCTYPE html>");
//		pw.println("<html lang=\"en\">");
//		pw.println("<head>");
//		pw.println("<meta charset=\"UTF-8\">");
//		pw.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
//		pw.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
//		pw.println("<title>" + book.getTitle() + "</title>");
//		pw.println("</head>");
//		pw.println("<body>");
//
//
//		pw.println("<div>");
//		pw.println("<h1>Titre : "  + book.getTitle() + "</h1>");
//
//		if(book.getImageId() != null) {
//			pw.println("<img src=\"image/" + book.getImageId() + "\" width=\"80\">");
//		}
//
//		pw.println("<div>Prix : "  + priceFmt.format(book.getPrice()) + " &euros</div>");
//		pw.println("<div>ISBN : "  + book.getIsbn() + "</div>");
//		pw.println("<div>Parution : "  +  sdf.format(book.getRelease())+ "</div>");
//		//pw.println("<div>Parution : "  + book.getDetails().getIsbn() + "</");
//
//
//		String authors = book.getAuthors()
//							.stream()
//							.map(a -> a.getName())
//							.collect(Collectors.joining(", "));
//		pw.println("<div>Auteurs : " + authors + "</div>");
//
//		if(!book.getTags().isEmpty()) {
//			String tags = book.getTags()
//							  .stream()
//							  .collect(Collectors.joining(", "));
//
//		pw.println("<div>Tags : " + tags + "</div>");
//		}
//
//		pw.println("<p>" + book.getOverview() + "</p>");
//		pw.println("</div>");
//		pw.println("</form>");
//		pw.println("</body>");
//		pw.println("</html>");
//
//
		}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{


		// On donne à servlet paramètres bookId & quantity
		int bookId = Integer.parseInt(req.getParameter("bookId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));

		if(log.isDebugEnabled()) {
			log.debug("Ajout au panier: " + bookId + ", quantité = " + quantity);
		}

		BookFullDto book = service.loadBookDetails(bookId);
		req.setAttribute("book", book);


		// Ensuite on récupère une session pour le panier
		// et s'il n'y a pas de panier on créer un nouveau
		BasketDto basket = (BasketDto) req.getSession().getAttribute("basket");

		if(basket == null) {
			basket = new BasketDto();


			CustomerDto cust = (CustomerDto) req.getSession().getAttribute("customer");
			basket.setCustomer(cust);

			req.getSession().setAttribute("basket", basket);

		}

		basket.addItem(book, quantity);

		if(log.isDebugEnabled()) {
			log.debug("Panier mis à jour : " + basket);
		}

		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/detail.jsp");
		rd.forward(req, resp);


	}

}


