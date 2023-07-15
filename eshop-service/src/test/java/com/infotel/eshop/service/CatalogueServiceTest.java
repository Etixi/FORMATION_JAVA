package com.infotel.eshop.service;

import static com.infotel.eshop.test.TestUtils.executeScript;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.infotel.eshop.config.TestConfig;
import com.infotel.eshop.dto.AuthorDto;
import com.infotel.eshop.dto.BookFullDto;
import com.infotel.eshop.dto.BookLightDto;
import com.infotel.eshop.dto.CategoryDto;
import com.infotel.eshop.test.TestUtils;

public class CatalogueServiceTest {
	
	private CatalogService service;
	
	@BeforeEach
	void init() throws Exception {
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context-test.xml");
		//service = ctx.getBean(CatalogService .class);
		ApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfig.class);
		executeScript("src/test/resources/scripts/clean-db-sql.sql");
		//service = new CatalogServiceImpl();
		//executeScript("src/test/resources/scripts/clean-db-sql.sql");
	}
	
	@Nested
	class It_Should_Book_Details{
	@Test
	void asterrix_le_gaullios()throws Exception{
		
		
		// Arrange : id; isbn; release; overview
		//CatalogService service = new CatalogServiceImpl();
		
		//int id = 20;
		BookFullDto book = service.loadBookDetails(1);				

//				
//		// assert
		Assertions.assertNotNull(book);
		Assertions.assertEquals(1, book.getId());
		Assertions.assertEquals(1, book.getImageId());
		Assertions.assertEquals(9.99, book.getPrice(), "Prix incorrect");
	    Assertions.assertEquals(LocalDate.parse("2004-06-16"), book.getRelease());
//		Assertions.assertEquals("Pour célébrer l’anniversaire de la bataille de Gergovie, les Gaulois accueillent leurs nombreux amis et s’offrent en apéritif les malheureux légionnaires du camp fortifié de Babaorum. Là, ils libèrent Ocatarinetabelatchitchix, chef de clan corse prisonnier des Romains. Ce dernier, plus fier encore qu’un Gaulois, prétend alors que les Corses sont le « cauchemar des romains ». Curieux de découvrir comment les Corses pratiquent le latin au quotidien, Astérix et Obélix décident de s’embarquer pour l’Ile de beauté, histoire de prouver qu’ils sont les seuls à donner d’authentiques sueurs froides aux Romains. Et ce sont encore ces derniers qui, malgré eux, vont arbitrer les débats…", book.getOverview(), "Resumé incorrecte");
//		Assertions.assertEquals("Comique", book.getTags().get(0), "Auteur incorrect");
//		Assertions.assertEquals("Jeunesse", book.getTags().get(1), "Auteur incorrect");
//		Assertions.assertEquals("René Goscinny", book.getAuthors().get(0).getName(), "Auteur incorrect");
//		Assertions.assertEquals("Albert Uderzo", book.getAuthors().get(1).getName(), "Auteur incorrect");
//		;
//		
	    Assertions.assertEquals("57d3cff23b463f990c9fc33d9c2d6e30c56471db", TestUtils.textToSha1(book.getOverview()));
		CategoryDto category = book.getCategory();
		Assertions.assertNotNull(category);
		Assertions.assertEquals(1, category.getId());
		Assertions.assertEquals("Aventure", category.getName());
		
		Assertions.assertNotNull(book.getTags());
		Assertions.assertTrue(book.getTags().isEmpty());
		
		List<AuthorDto> authors =book.getAuthors();
		Assertions.assertNotNull(authors);
		Assertions.assertEquals(2, authors.size());
		
		
		AuthorDto author1 = authors.get(0);
        Assertions.assertNotNull(author1);
        Assertions.assertEquals(1, author1.getId());
        Assertions.assertEquals("René Goscinny", author1.getName());
       
        AuthorDto author2 = authors.get(1);
        Assertions.assertNotNull(author2);
        Assertions.assertEquals(2, author2.getId());
        Assertions.assertEquals("Albert Uderzo", author2.getName());

        
        
////		assertThat(book).isNotNull()
////						.extracting("id", "title", "isbn","release", "price", "imageId")
////						.containsExactly(1, "Astérix le Gaulois", "978-2012101333", LocalDate.parse("2004-06-16"),9.99, 1);
////		assertThat(book.getOverview()).startsWith("Dans le camp fortifié romain").endsWith("ses talents de stratège");
	}
}	
	
	@Test
    void testLoadCategories() throws Exception {
       // CatalogService service = new CatalogServiceImpl();

        List<CategoryDto> categories = service.loadCategories();

        Assertions.assertNotNull(categories);
        Assertions.assertEquals(5, categories.size());

        for (CategoryDto category : categories) {
            Assertions.assertNotNull(category);
        }

        CategoryDto cat1 = categories.get(0);
        Assertions.assertEquals(1, cat1.getId());
        Assertions.assertEquals("Aventure", cat1.getName());

        CategoryDto cat2 = categories.get(1);
        Assertions.assertEquals(2, cat2.getId());
        Assertions.assertEquals("Policier", cat2.getName());

        CategoryDto cat3 = categories.get(2);
        Assertions.assertEquals(3, cat3.getId());
        Assertions.assertEquals("Historique", cat3.getName());

        CategoryDto cat4 = categories.get(3);
        Assertions.assertEquals(4, cat4.getId());
        Assertions.assertEquals("Fantastique", cat4.getName());

        CategoryDto cat5 = categories.get(4);
        Assertions.assertEquals(5, cat5.getId());
        Assertions.assertEquals("Manga", cat5.getName());
    }
	
	@Test
    void testSearchBooks() throws Exception {
        //CatalogService service = new CatalogServiceImpl();

        List<BookLightDto> books = service.searchBooks("tintin", -1);

        Assertions.assertNotNull(books);
        Assertions.assertEquals(10, books.size());

        for (BookLightDto book : books) {
            Assertions.assertNotNull(book);
        }

        BookLightDto book1 = books.get(0);
        Assertions.assertNotNull(book1);
        Assertions.assertEquals(39, book1.getId());
        Assertions.assertEquals(39, book1.getImageId());
        Assertions.assertEquals(11.55, book1.getPrice());
        Assertions.assertEquals("Tintin au pays des soviets", book1.getTitle());

        BookLightDto book2 = books.get(4);
        Assertions.assertNotNull(book2);
        Assertions.assertEquals(43, book2.getId());
        Assertions.assertEquals(43, book2.getImageId());
        Assertions.assertEquals(11.5, book2.getPrice());
        Assertions.assertEquals("Le Lotus bleu", book2.getTitle());

        BookLightDto book3 = books.get(9);
        Assertions.assertNotNull(book3);
        Assertions.assertEquals(48, book3.getId());
        Assertions.assertEquals(48, book3.getImageId());
        Assertions.assertEquals(11.5, book3.getPrice());
        Assertions.assertEquals("L'Etoile mystérieuse", book3.getTitle());
    }
}
