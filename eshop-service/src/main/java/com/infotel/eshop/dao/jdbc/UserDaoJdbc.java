package com.infotel.eshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.springframework.stereotype.Component;

import com.infotel.eshop.dao.UserDao;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.Merchant;
import com.infotel.eshop.model.User;


//@Component("userDAO")
public class UserDaoJdbc extends AbstractDaoJdbc implements UserDao {
	
	
	//private final static Logger log = LogManager.getLogger(UserDaoJdbc.class);
	@Override
	public User findOneByUsername(String username) {

		User user = null;
		String sql = "select username, user_type, password, first_name, last_name, title, phone "
				+ "from user_data where username = ?";

		/*    2eme méthode
		 * "select username, user_type, password, first_name, last_name, title, phone "
		 * + "from user_data where username ='"+username+"'";
		 */

		try(
				Connection cn = getConnection();
				PreparedStatement  st = cn.prepareStatement(sql);
				) 

		{
			st.setString(1, username);
			try(ResultSet rs = st.executeQuery();){


				while(rs.next()) {
					

					String type = rs.getString(2); 
					/* String usernamedb = rs.getString(1); 
					 * String password = rs.getString(3); String first_name =
					 * "null".equals(rs.getString(4))? null : rs.getString(4); String last_name =
					 * "null".equals(rs.getString(5))? null : rs.getString(5); String title =
					 * "null".equals(rs.getString(6)) ? null : rs.getString(6); String phone =
					 * "null".equals(rs.getString(7)) ? null : rs.getString(7);
					 */
					switch(type) {

					case "CU" -> {
						Customer cust = new Customer();
						cust.setUsername(rs.getString(1));
						cust.setPassword(rs.getString(3));
						cust.setFirstName(rs.getString(4));
						cust.setLastName(rs.getString(5));
						cust.setTitle(rs.getString(6));
						cust.setPhone(rs.getString(7));

						user =  cust;
					}

					case "ME" -> {
						Merchant merchant = new Merchant();
						merchant.setUsername(rs.getString(1));
						merchant.setPassword(rs.getString(3));
						merchant.setFirstName(rs.getString(4));
						merchant.setLastName(rs.getString(5));

						user = merchant;
					}
					}
				}
			}
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new EShopException("Problème accès lecture de utilisateurs" + username, e) ;
		} 


		return user;
	}


	@Override
	public void create(User user) {

		

			String sql = "insert into user_data (username, user_type, password, first_name, last_name, title, phone) values(?, ?, ?, ?, ?, ?, ?)";	


			try (
					Connection cn = getConnection();
					PreparedStatement  st = cn.prepareStatement(sql)
					)
			{
				
				st.setString(1, user.getUsername());
				st.setString(3, user.getPassword());
				
//				if(log.isDebugEnabled()) {
//				log.debug("Insertion de l'utitlisateur: " + user);
//				}
				
				if (user instanceof Customer) {
				Customer cust = (Customer)user;
			
				st.setString(2, "CU");
				st.setString(4, cust.getFirstName());
				st.setString(5, cust.getLastName());
				st.setString(6, cust.getPhone());
				st.setString(7, cust.getTitle());

				

			}
				else if(user instanceof Merchant) {

			}
			st.executeUpdate();
			} 
			catch(ClassNotFoundException | SQLException | NamingException e) {
			throw new EShopException("Problème insertion de l'utilisateur : " + user, e) ;

			}
				
	}

}


	

	
	
	

