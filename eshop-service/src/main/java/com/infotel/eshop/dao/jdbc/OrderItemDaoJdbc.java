package com.infotel.eshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.infotel.eshop.dao.OrderItemDao;
import com.infotel.eshop.dto.BookLightDto;
import com.infotel.eshop.dto.OrderItemDto;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Book;
import com.infotel.eshop.model.Order;
import com.infotel.eshop.model.OrderItem;

public class OrderItemDaoJdbc  extends AbstractDaoJdbc implements OrderItemDao{


	@Override
	public void create(OrderItem item) {

		String sql = "insert into order_item (position, quantity, unit_price, book_id, order_id) "
				+ "VALUES (0, ?, ?, ?, ?)";

		try (
				Connection cn = getConnection();
				PreparedStatement st = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				){

			// quantity int 
			st.setInt(1, item.getQuantity());
			// unit_price decimal
			st.setDouble(2, item.getUnitPrice());
			// Book_id int
			st.setInt(3, item.getBook().getId());
			st.setInt(4, item.getOrder().getId());
			st.executeUpdate();


			try (ResultSet rs = st.getGeneratedKeys()){
				if (rs.next()) {
					item.setId(rs.getInt(1));
				}
			}
		} 

		catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new EShopException("Problème insertion de la commande : " + item, e);
		}
	}

	@Override
	public List<OrderItem> findManyByByOrder(int orderId) {
		List<OrderItem> items = new ArrayList<>();
		
		String sql = "select oi.id, oi.quantity, oi.unit_price,oi.book_id,  b.title "
				+ "from order_item oi inner join book b "
				+ "on oi.id = b.id "
				+ "where order_id = ?";

		try(	

				Connection cn = getConnection();
				PreparedStatement  st  = 
						cn.prepareStatement(sql);
				)
		{
			st.setInt(1, orderId);
	
			try (ResultSet rs = st.executeQuery()){
				if(rs.next()) {
					
			OrderItem item = new OrderItem();
			Book book = new Book();
			book.setId(rs.getInt(4));
			book.setTitle(rs.getString(5));
			item.setBook(book);
			
			item.setId(rs.getInt(1));
			// quantity int 
			item.setQuantity(rs.getInt(2));
			// unit_price decimal
			item.setUnitPrice(rs.getDouble(3));
			
			items.add(item);
			
				}
			}	 
		} 

		catch(SQLException | ClassNotFoundException | NamingException e) {
			throw new EShopException("Problème lecture de  commande! " + orderId, e);
		}
		return items;
	}

	
	}



