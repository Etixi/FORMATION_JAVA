package com.infotel.eshop.service;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static com.infotel.eshop.test.TestUtils.executeScript;
import static com.infotel.eshop.test.TestUtils.getDbSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.core.util.Arrays;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.infotel.eshop.config.TestConfig;
import com.infotel.eshop.dto.AddressDto;
import com.infotel.eshop.dto.CustomerDto;
import com.infotel.eshop.dto.LoginDto;
import com.infotel.eshop.dto.RegisterDto;
import com.infotel.eshop.exception.AuthException;

public class AcountServiceTest {
	
	private AccountService service;
	

	
	@BeforeEach
	void init() throws Exception {
		
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context-test.xml");
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfig.class);
		
		service =  ctx.getBean(AccountService.class);
		
		executeScript("src/test/resources/scripts/clean-db-sql.sql");
	}
	
	
	@Nested
	class it_Should_Authenticate{
		
	@Test
	void emile_lampion() throws Exception{
		
		// Arrange
		LoginDto login = new LoginDto();
		login.setUsername("emile@mailo.fr");
		login.setPassword("pomme");
		
		// act
		
		CustomerDto cust = service.authentauthenficateCustomer(login);
		
		// assert
		
//		Assertions.assertNotNull(cust, "Client null");
//		Assertions.assertEquals( "jordan@gmail.com", cust.getUsername(),"Identifiant incorrect");
//		Assertions.assertEquals("Jordan", cust.getFirstName(), "Prenom incorrect");
//		Assertions.assertEquals("Jean", cust.getLastName(), "Nom incorrect");
//		Assertions.assertEquals("mister", cust.getTitle(), "Civilité incorrecte");
//		Assertions.assertEquals("0639284095", cust.getPhone(), "Telephone incorrect");
		
		assertThat(cust).isNotNull()
						.extracting("username", "firstName", "lastName", "title", "phone")
						.containsExactly("emile@mailo.fr", "Émile", "Lampion", "mister", null);
		
		AddressDto addr = cust.getAddress();
		assertThat(addr).isNotNull().extracting("street", "zip", "city").containsExactly("10 rue de belleville", "75020", "Paris");
		
	}
}
	@Nested
	class Authenticate_Should_Failed{
		
	
	@Test 
	void when_password_is_incorrect() throws Exception{
		
		// Arrange
		//AccountService service = new AccountServiceImpl();
		LoginDto login = new LoginDto();
		login.setUsername("emile@mailo.fr");
		login.setPassword("xxx");
		
		// act
		//Assertions.assertThrows(AuthException.class, 
		//						() -> service.authentauthenficateCustomer(login));
		assertThatExceptionOfType(AuthException.class).isThrownBy(() -> service.authentauthenficateCustomer(login))
														.withNoCause()
														.withMessage("Echec de l'authentification");
	}
	
	@Nested 
	class it_should_register_user{
		@Test @Disabled
		void when_animal_data_is_entered() throws Exception{
			
			//AccountService service = new AccountServiceImpl();
			RegisterDto register = new RegisterDto();
			register.setUsername("emile@mailo.fr");
			register.setPassword("pomme");
			register.setFirstName("Émile");
			register.setLastName("Lampion");
			
			//service.registerCustomer(register);
			
			
			Table table = new Table(getDbSource(), "user_data",
					Arrays.array(Table.Order.asc("username")));
			
			assertThat(table).hasNumberOfRows(5)
							.row(3)
							.value("username").isEqualTo("emile@mailo.fr")
							.value("user_type").isEqualTo("CU")
							.value("password").isEqualTo("pomme")
							.value("first_name").isEqualTo("Émile")
							.value("last_name").isEqualTo("Lampion")
							.value("title").isEqualTo("mister")
							.value("phone").isNull();
		}
	}

  }
}
