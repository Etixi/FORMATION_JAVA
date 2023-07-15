package com.infotel.eshop.ui;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.infotel.eshop.dto.BookLightDto;
import com.infotel.eshop.dto.CategoryDto;
import com.infotel.eshop.service.CatalogService;
import com.infotel.eshop.service.CatalogServiceImpl;
import com.infotel.eshop.service.OrderService;



public class SearchWindow extends Window {

	private CatalogService service;   //= new CatalogServiceImpl();
	private List<BookLightDto> books;



	public SearchWindow() {
		super("Recherche");
		
		ApplicationContext ctx = Session.Instance.getAttribute("context");;
		this.service = ctx.getBean(CatalogService.class);

	}

	protected void renderBody(NavigationRequest request) {
		renderBody();
	}
	protected void renderBody() {
		this.books = Session.Instance.getAttribute("books");
		
		if (this.books == null || this.books.isEmpty()) {
			renderSearch();
		Session.Instance.setAttribute("boos", books);
		}
		
		renderResult();
		renderMenu();

	}


	protected  void renderSearch(){
		
		List<CategoryDto> categories = service.loadCategories();

		render("Categories :");

		//for(int i = 0; i < categories.size(); i++) {
		//	renderCategoryItem(categories, i);

//		}

		//=================================================================================//
		categories.stream().forEach(c->renderCategoryItem(c));
		render("9 - Toutes les catégories");
		render();
		

		render("Category ?");
		int selectedCatLine = readInputInt();

		CategoryDto category = null;
		if (selectedCatLine > 0 && selectedCatLine <= categories.size()) {
			category = categories.get(selectedCatLine-1);
		}

		render("Mot de clé :");
		String keyword = readInputText();
		int categoryId = category == null ? -1 : category.getId(); //readInputInt();


		books = service.searchBooks(keyword, categoryId);


	}

	private void renderCategoryItem(CategoryDto c) {
		//Category category = categories.get(i);
		
		render(c.getId()+ " - " + c.getName());
		//int lineNumber = i+1;
		//render(lineNumber  + " " + category.getName());
	}

	//=================================================================================//


	private void renderResult() {
		render("Resultats de la recherche: " + books.size() + "éléments trouvés");

		for(int i = 0; i < books.size(); i++) {
			BookLightDto book = books.get(i);
			if(book == null) continue;
			int lineNumber = i + 1;
			String line = String.format("%s - %-20s (%s) - %5.2f €",
					lineNumber, book.getTitle(), book.getId(), book.getPrice());
			render(line);}
	}


	private void renderMenu() {
		render();
		render("Sélectionner une action :");
		render("1. Détail");
		render("2. Nouvelle recherche");
		render("3. Retour");

		String searchChoice = readInputText();



		//§ Selection du livre pour détail

		switch(searchChoice) {
		case "1" -> {

			// selection du livre pour détail
			renderDetail();	}

		case "2" -> {
			render(" ");
			//render("Retour page search ");
			this.books = null;
			Session.Instance.removeAttribute("books");
			renderBody();
			//navigate("search");
			}

		case "3" -> {
			render(" ");
			render("Retour (accueil");
			navigate("home");}
		}

	}


	private void renderDetail() {
		render();
		render("Choisir un livre");
		int choice = readInputInt();
		BookLightDto selectedBook = books.get(choice-1);
		navigate("detail", selectedBook.getId());
	} 

}