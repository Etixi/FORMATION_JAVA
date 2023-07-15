package com.infotel.eshop.web;

import java.io.IOException;
import java.io.OutputStream;

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

import com.infotel.eshop.dto.ImageDataDto;
import com.infotel.eshop.service.CatalogService;
import com.infotel.eshop.service.CatalogServiceImpl;

@WebServlet(urlPatterns = "/image/*")

public class ImageServlet extends HttpServlet {

	private static final Logger log = LogManager.getLogger(ImageServlet.class);

	private CatalogService service;//  = new CatalogServiceImpl();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		service = ctx.getBean(CatalogService.class);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{


		if(log.isDebugEnabled()) {
			log.debug("URI de l'image : " + req.getRequestURI());
		}


		String uri = req.getRequestURI();
		String imageIdstr = uri.substring(uri.lastIndexOf("/") + 1);
		int imageId = Integer.parseInt(imageIdstr);

		//int imageId = Integer.parseInt(req.getParameter("id"));

		if(log.isDebugEnabled()) {
			log.debug("Chargement de l'image : " + imageId);
		}

		ImageDataDto image = service.loadImage(imageId);

//		resp.setContentType("image/png");
//		resp.setContentLength(image.getContent().length);
//
//		OutputStream os = resp.getOutputStream();
//		os.write(image.getContent());
//
		if(image != null){

			resp.setContentType("image/png");
			resp.setContentLength(image.getContent().length);

			OutputStream os = resp.getOutputStream();
			os.write(image.getContent());

		}else {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}

	}
}
