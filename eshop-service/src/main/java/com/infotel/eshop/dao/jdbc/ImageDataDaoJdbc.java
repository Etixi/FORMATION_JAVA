package com.infotel.eshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.naming.NamingException;

import com.infotel.eshop.dto.ImageDataDto;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Book;
import com.infotel.eshop.model.BookDetails;
import com.infotel.eshop.model.BookPrice;
import com.infotel.eshop.model.Category;
import com.infotel.eshop.model.ImageData;

public class ImageDataDaoJdbc extends AbstractDaoJdbc implements ImageDataDao{

	@Override
	public ImageData findOneById(int id) {
		ImageData image = null;

		String sql = "select id, target, target_id, content "
				+ "from image_data "
				+ "where id = ? ";
				



		try(
				Connection cn = getConnection();
				PreparedStatement  st = cn.prepareStatement(sql);
			) 

		{    
		
			st.setInt(1, id);

			try(ResultSet rs = st.executeQuery();){


			
				while(rs.next()) {

					image = new ImageData();
					image.setId(rs.getInt(1));
					image.setTarget(rs.getString(2));
					image.setTargetId(rs.getString(3));
					image.setContent(rs.getBytes(4));

				}
			}
		} 
		

		catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new EShopException("Problème lecture de l'image " + id,  e) ;
		}

		return image; 
	}

	@Override
	public Integer findOneByEntity(String target, String targetId) {
		
		Integer id = null;
		String sql = "select id "
				+ "from image_data "
				+ "where target = ? and target_id = ?";
				



		try(
				Connection cn = getConnection();
				PreparedStatement  st = cn.prepareStatement(sql);
			) 

		{    
		
			st.setString(1, target);
			st.setString(2, targetId);

			try(ResultSet rs = st.executeQuery();){


			
				while(rs.next()) {

					id = rs.getInt(1);

				}
			}
		} 
		

		catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new EShopException("Problème lecture de l'image pour l'entité" + target + "/" + targetId,  e) ;
		}

		return id;
	}

}
