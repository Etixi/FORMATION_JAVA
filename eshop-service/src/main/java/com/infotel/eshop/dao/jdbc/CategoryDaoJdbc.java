package com.infotel.eshop.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.infotel.eshop.dao.CategoryDao;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Category;

public class CategoryDaoJdbc extends AbstractDaoJdbc implements CategoryDao{

	@Override
	public List<Category> findAll() {
		
		List<Category> categories = new ArrayList<>();
		
		
		try(
				Connection cn = getConnection();
				Statement st = cn.createStatement();
			) 
		
		{
			String sql = "select id, name from category";
	
		try(ResultSet rs = st.executeQuery(sql);){
			
		
			while(rs.next()) {
				int id = rs.getInt(1); // soit id
				String name = rs.getString(2); // soit name
				Category cat = new Category(id, name);
				categories.add(cat);
			}
		}

		
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new EShopException("Problème accès lecture des catégories", e) ;
		} 
		
		
		return categories;
	}

}
