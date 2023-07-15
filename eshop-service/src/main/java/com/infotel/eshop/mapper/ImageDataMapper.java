package com.infotel.eshop.mapper;

import com.infotel.eshop.dto.ImageDataDto;
import com.infotel.eshop.model.ImageData;

public interface ImageDataMapper {
	
	static ImageDataDto imageDataToImageDataDto(ImageData image) {
        if (image == null) return null;
        ImageDataDto dto = new ImageDataDto();
        dto.setId(image.getId());
        dto.setContent(image.getContent());

        return dto;
    }
}
