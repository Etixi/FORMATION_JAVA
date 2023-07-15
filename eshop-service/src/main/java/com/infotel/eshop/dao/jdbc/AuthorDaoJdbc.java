package com.infotel.eshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.infotel.eshop.dao.AuthorDao;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Author;

public class AuthorDaoJdbc extends AbstractDaoJdbc implements AuthorDao{

	@Override
	public List<Author> findManyByBook(int bookId) {
		List <Author> authors = new ArrayList<>();
		
		String sql = "select a.id, a.name "
				+ "from author a "
				+ "inner join book_author ba on ba.author_id = a.id "
				+ "where ba.book_id = ? ";
		
			
		try(
				Connection cn = getConnection();
				PreparedStatement st = cn.prepareStatement(sql);
			) 
		
		{    
			
			st.setInt(1, bookId);
			
			
		try(ResultSet rs = st.executeQuery();){
			
			
			while(rs.next()) {
				
			Author author = new Author();
			author.setId(rs.getInt(1));
			author.setName(rs.getString(2));
			authors.add(author);
			}
		  }
		} 
		
		catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new EShopException("Problème lecture des auteurs du livre " + bookId,  e) ;
		}
		
		return authors; 
		
		}

	
	}


