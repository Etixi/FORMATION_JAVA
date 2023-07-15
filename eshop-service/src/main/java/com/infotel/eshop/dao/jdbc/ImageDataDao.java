package com.infotel.eshop.dao.jdbc;

import com.infotel.eshop.model.ImageData;

public interface ImageDataDao {
	
	ImageData findOneById(int id);
	Integer findOneByEntity(String target, String targetId);
}
