

package com.infotel.eshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.infotel.eshop.dao.BookDao;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Book;
import com.infotel.eshop.model.BookDetails;
import com.infotel.eshop.model.BookPrice;
import com.infotel.eshop.model.Category;

public class BookDaoJdbc extends AbstractDaoJdbc implements BookDao {


	@Override
	public Book findOneById(int id) {


		Book book = null;

		String sql = "select b.id, b.title, b.price_value, b.price_updated, c.id, c.name, "
				+ "d.id, d.overview, d.isbn, d.volume, d.series, d.release "
				+ "from book b "
				+ "inner join category c on c.id = b.category_id "
				+ "inner join book_details d on d.id = b.id "
				+ "where b.id = ?";



		try(
				Connection cn = getConnection();
				PreparedStatement  st = cn.prepareStatement(sql);
			) 

		{    
		
			st.setInt(1, id);

			try(ResultSet rs = st.executeQuery();){


			
				while(rs.next()) {

					book = new Book();
					book.setId(rs.getInt(1));
					book.setTitle(rs.getString(2));
		
					
					BookPrice price = new BookPrice();
					price.setValue(rs.getDouble(3));
					price.setUpdated(rs.getObject(4, LocalDateTime.class));
					book.setPrice(price);
					
					Category cat = new Category();
					cat.setId(rs.getInt(5));
					cat.setName(rs.getString(6));
					book.setCategory(cat);

					BookDetails details = new BookDetails();
					details.setId(rs.getInt(7));
					details.setOverview(rs.getString(8));
					details.setIsbn(rs.getString(9));
					details.setRelease(rs.getObject(12, LocalDate.class));
					book.setDetails(details);
					
					List <String> tags = getTagByBook(id);
					book.setTags(tags);
					

				}
			}
		} 
		

		catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new EShopException("Problème lecture du livre " + id,  e) ;
		}

		return book; 
	}

	private List<String> getTagByBook(int bookId){

		List <String> tags = new ArrayList<>();

		String sql = "select tag "
				+ "from book_tag bt "
				+ "where bt.book_id = ? ";


		try(
				Connection cn = getConnection();
				PreparedStatement st = cn.prepareStatement(sql);
			
			) 

		{    
			st.setInt(1, bookId);
			try(ResultSet rs = st.executeQuery();)
			
			{
				
				String tag = null;
				while(rs.next()) {
					tag = rs.getString(1);
					tags.add(tag);
					
				}
			}
		} 

		catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new EShopException("Problème lecture des tags du livre" + bookId,  e) ;
		}

		return tags; 

	}




	@Override
	public List<Book> findManyByCriteria(String keyword, int categoryId){

		List <Book> books = new ArrayList<>();

		String sql = "select b.id, b.title, b.price_value, b.price_updated from book b "
				+ "inner join book_details d on d.id = b.id "
				+ "where 1 = 1 ";

		if(keyword != null && !keyword.isBlank()) {
			sql += " and (lower(title) like ? or lower(d.series) like ?)";
		}

		if(categoryId != -1) {
			sql += " and b.category_id = ?";
		}
		
		sql += " limit 10";

		try(
				Connection cn = getConnection();
				PreparedStatement  st = cn.prepareStatement(sql);
				) 

		{    

			int count = 0;
			if(keyword != null && !keyword.isBlank()) {
				st.setString(++count, "%" + keyword.toLowerCase() + "%");
				st.setString(++count, "%" + keyword.toLowerCase() + "%");
			}
			if(categoryId != -1) {
				st.setInt(++count, categoryId);
			}


			try(ResultSet rs = st.executeQuery();){


				while(rs.next()) {

					Book book = new Book();
					book.setId(rs.getInt(1));
					book.setTitle(rs.getString(2).trim());
					
					BookPrice price = new BookPrice();
					price.setUpdated(rs.getObject(4, LocalDateTime.class));
					price.setValue(rs.getDouble(3));
					book.setPrice(price);
					

					books.add(book);
				}
			}
		} 

		catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new EShopException("Problème accès aux livres " + keyword + " / " + categoryId +  e) ;
		}

		return books; 

	}
}
