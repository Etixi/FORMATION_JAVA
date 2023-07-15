package com.infotel.eshop.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotel.eshop.dto.ImageDataDto;
import com.infotel.eshop.service.CatalogService;

@RestController @RequestMapping("/images")
public class ImageController {
	
	@Autowired
	private CatalogService service;
	
	@GetMapping("/id")
	public ResponseEntity<byte[]> getImage(@PathVariable("id") int id){
		
		//ImageDataDto loadImage(int imageid);
		ImageDataDto image = service.loadImage(id);
		
		if(image == null) {
			return ResponseEntity.notFound().build();
		}
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.IMAGE_PNG);
		headers.setContentLength(image.getContent().length);
		return ResponseEntity.ok().headers(headers).body(image.getContent());
		
	}

}
