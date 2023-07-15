package com.infotel.eshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import com.infotel.eshop.dao.ItemCounterDao;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.ItemCounter;

public class ItemCounterDaoJdbc extends AbstractDaoJdbc implements ItemCounterDao{

	
	
	@Override
	public ItemCounter findOneByItem(String item) {
		
		String sql = "select item, item_value from item_counter where item= ?";
				
		//ItemCounter i = null;
		
		try (
				Connection cn = getConnection();
				PreparedStatement st = cn.prepareStatement(sql);

				){

			st.setString(1, item);
		
			//st.executeUpdate();
			
			
			try (ResultSet rs = st.executeQuery()){
				if (rs.next()) {
					ItemCounter i = new ItemCounter();	
					i.setItem(rs.getString(1));
					i.setValue(rs.getInt(2));
					return i;
				}
		
			}
		}	catch (ClassNotFoundException | SQLException | NamingException e) {
				throw new EShopException("Echec de la lecture du compteur : " + item, e);
			}
		
		return null;
		}	
		
			
	@Override
	public void create(ItemCounter count) {
		
		
		String sql = "insert into item_counter (item, item_value) "
				+ "VALUES (?, ?)";
		

		try (
				Connection cn = getConnection();
				PreparedStatement ps = cn.prepareStatement(sql);

				){

			
			ps.setString(1, count.getItem());
			ps.setInt(2, count.getValue());
			ps.executeUpdate();
			}

		catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new EShopException("Problème insertion de la commande : " + count, e);
		}
	}
	


	@Override
	public void update(ItemCounter count) {
		String sql = "update item_counter set "
				+ "item_value = ? where item = ?";
				
		

		try (
				Connection cn = getConnection();
				PreparedStatement ps = cn.prepareStatement(sql);

				){
			
			ps.setInt(1, count.getValue());
			ps.setString(2, count.getItem());
			ps.executeUpdate();
		} 

		catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new EShopException("Problème mise à jour de la commande : " + count, e);
		}
		
	}

}
