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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.infotel.eshop.dto.BookLightDto;
import com.infotel.eshop.dto.CategoryDto;
import com.infotel.eshop.service.CatalogService;

@WebServlet("/search")
public class SearchServlet extends HttpServlet{

	private final static Logger log = LogManager.getLogger(SearchServlet.class);
	private CatalogService service; // = new CatalogServiceImpl();
	//private static Logger log = Logger.
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		service = ctx.getBean(CatalogService.class);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		List<BookLightDto> books = null;

		if(req.getParameter("catId") != null) {

			String keyword = req.getParameter("keyword");
			int categoryId = Integer.parseInt(req.getParameter("catId"));

			//String leNom = req.getParameter("nom");
			//String lePrenom = req.getParameter("prenom");


			if(log.isDebugEnabled()) {
				log.debug("Recherche de livres : " + keyword + " / " + categoryId);
			}

			books = service.searchBooks(keyword, categoryId);
			req.setAttribute("books", books);

		}


		List<CategoryDto> categories = service.loadCategories();
		req.setAttribute("categories", categories);


		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
		rd.forward(req, resp);


	}

}
