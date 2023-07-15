package com.infotel.eshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.infotel.eshop.dao.OrderDao;
import com.infotel.eshop.dto.OrderDto;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.Order;
import com.infotel.eshop.model.OrderStatus;

public class OrderDaoJbdc extends AbstractDaoJdbc implements OrderDao{

	@Override
	public void create(Order order) {



		String sql = "insert into purchase_order(order_number, created, status, customer_id) "
				+ "values(?, ?, ?, ?)";

		try(	

				Connection cn = getConnection();
				PreparedStatement  st  = 
						cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				)
		{
			st.setString(1, order.getNumber());
			
			//Timestamp created = new Timestamp(order.getCreated().getTime());
			st.setString(1, order.getNumber());
			st.setObject(2, order.getCreated());
			st.setString(3, order.getStatus().name());
			st.setString(4, order.getCustomer().getUsername());
			st.executeUpdate();

			try (ResultSet rs = st.getGeneratedKeys()){
				if(rs.next()) {
					order.setId(rs.getInt(1));
				}
			}	 
		} 

	
		
		catch(SQLException | ClassNotFoundException | NamingException e) {
			throw new EShopException("Problème d'insertion de la commande" + order, e);
		}
	}

	@Override
	public List<Order> findManyByCustomer(String username) {
		
		List<Order> orders = new ArrayList<>();
		
		String sql = "select id, order_number, status, created, customer_id from purchase_order where customer_id = ?"
				+ "order by created desc";

		try(	

				Connection cn = getConnection();
				PreparedStatement  st  = 
						cn.prepareStatement(sql);
				)
		{
			st.setString(1, username);
	
			try (ResultSet rs = st.executeQuery()){
				if(rs.next()) {
					
			Order order = new Order();	
			order.setId(rs.getInt(1));
			order.setNumber(rs.getString(2));
			OrderStatus status = OrderStatus.valueOf(rs.getString(3));
			order.setCreated(rs.getObject(4, LocalDateTime.class));
			order.setStatus(status);
			Customer cust = new Customer();
			cust.setUsername(rs.getString(5));
			order.setCustomer(cust);
			//orders.add(order);
			orders.add(order);
				}
			}	 
		} 

		catch(SQLException | ClassNotFoundException | NamingException e) {
			throw new EShopException("Problème lecture des commandes de l'utilisateur : " + username, e);
		}
		return orders;
	}






}
