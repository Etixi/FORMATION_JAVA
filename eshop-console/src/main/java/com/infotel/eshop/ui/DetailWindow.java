package com.infotel.eshop.ui;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.infotel.eshop.dto.AuthorDto;
import com.infotel.eshop.dto.BasketDto;
import com.infotel.eshop.dto.BookFullDto;
import com.infotel.eshop.model.Basket;
import com.infotel.eshop.service.CatalogService;
import com.infotel.eshop.service.CatalogServiceImpl;
import com.infotel.eshop.service.OrderService;


public class DetailWindow extends Window{

	private CatalogService service; //  = new CatalogServiceImpl();
	public DetailWindow() {
		super("Détail");
		
		ApplicationContext ctx = Session.Instance.getAttribute("context");
		this.service = ctx.getBean(CatalogService.class);
	};

	
	private BookFullDto book;

	@Override
	protected void renderBody(NavigationRequest request){
		loadData(request); // on cast avec book
		renderDetail();
		renderMenu();
	}	
	private void loadData(NavigationRequest request) {
		int bookId = (Integer) request.getParams().get(0);
		this.book = service.loadBookDetails(bookId);
	}

	private void renderDetail() {
		
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter sdfPrice = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
		//SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		//SimpleDateFormat sdfPrice= new SimpleDateFormat("dd/MM/yyyy hh:mm");
		DecimalFormat priceFmt = new DecimalFormat("0.0");

		// Afichage des resultats

		render("Livre #");
		render("ID  : " + book.getId());
		render("Titre : " + book.getTitle().toUpperCase());
		render("Prix : " + priceFmt.format(book.getPrice()) + " €");// + " € (" + sdfPrice.format(book.getPrice().getUpdated()) + ")");
		render("ISBN : " + book.getIsbn());
		render("Parution : " + sdf.format(book.getRelease()));
		render("overview : " + book.getOverview());
		
		StringJoiner authorsJoiner = new StringJoiner(", ");
		
		//sbAuthors.deleteCharAt(author.getName())
		for(AuthorDto author: book.getAuthors()) {
			authorsJoiner.add(author.getName());
		}
		
		
		StringJoiner tagsJoiner = new StringJoiner(", ");
		
		//sbAuthors.deleteCharAt(author.getName())
		
		
		if(!book.getTags().isEmpty()) {
		for(String tag: book.getTags()) {
			tagsJoiner.add(tag);
			}
		render("Tag : " + tagsJoiner);
		}
		
		render("Auteurs : " + authorsJoiner);
		render("Resumé : " );
		//render("Resumé  :" + book.getDetails().getOverview());


		String overview = book.getOverview();
		int lineSize = 80;
		int count = 0;

		while (count < overview.length()) {
			int offset = count;
			render(overview.substring(Math.min(offset, overview.length() - 1),
					Math.min(offset + lineSize -1, overview.length() - 1)));
			count += lineSize;
		}
	}

	private void renderMenu() {
		render();
		render("1.Ajouter au panier");
		render("2.retour");
		render();

		render("votre choix");
		String choice = readInputText();

		switch(choice) {
		case "1" -> {
			addToBasket();
			renderMenu();
		}
		case "2" -> {
			navigate("home");
		}
		}
	}


	private void addToBasket() {
		render();
		render("Quantité :" );
		int quantity = readInputInt();

		Session session = Session.Instance;//getSession();
		BasketDto basket = session.getAttribute("basket");
		if(basket == null) {
			basket = new BasketDto();
			session.setAttribute("basket", basket);
		}
		basket.addItem(book, quantity);

		navigate("home");

	}

}
