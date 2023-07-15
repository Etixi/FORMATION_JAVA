package com.infotel.eshop.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotel.eshop.dao.BookDao;
import com.infotel.eshop.dao.CategoryDao;
import com.infotel.eshop.dao.jdbc.ImageDataDao;
import com.infotel.eshop.dto.BookFullDto;
import com.infotel.eshop.dto.BookLightDto;
import com.infotel.eshop.dto.CategoryDto;
import com.infotel.eshop.dto.ImageDataDto;
import com.infotel.eshop.mapper.BookMapper;
import com.infotel.eshop.mapper.CategoryMapper;
import com.infotel.eshop.mapper.ImageDataMapper;
import com.infotel.eshop.model.Book;
import com.infotel.eshop.model.ImageData;

@Service @Transactional
public class CatalogServiceImpl implements CatalogService {
	
	private static final Logger log = LogManager.getLogger(CatalogService.class);
	
	
	
	
	@Autowired
	private BookDao bookDao; //= new BookDaoJpa();
	
	@Autowired
	private CategoryDao categoryDao;// = new CategoryDaoJpa();
	
	@Autowired
	//private AuthorDao authorDao = new AuthorDaoJdbc();
	private ImageDataDao imageDataDao; //= new ImageDataDaoJpa();
	
	@Override
	public List<BookLightDto> searchBooks(String keyword, int categoryId) {
		
		List<Book> books = bookDao.findManyByCriteria(keyword, categoryId);
		
		
		return books.stream()
				.map(b -> BookMapper.bookToBookLightDto(b, getImageIdByBook(b.getId())))
				.collect(Collectors.toList());
		
	}
	
	
	@PostConstruct
	private void init() {
		log.info("initialisation du service");

	}
	
	@PreDestroy
	private void destroy() {
		log.info("destruction du service");
		
	}
	
	@Override
	public BookFullDto loadBookDetails(int id) {
		
		Book book = bookDao.findOneById(id);
		
		if(book==null) return null;
		//On invoque ici AuthorDao
		
		//List<Author> authors =  authorDao.findManyByBook(id);
		//book.setAuthors(authors);
		Integer imageId = getImageIdByBook(id);
		 
		return BookMapper.bookToBookFullDto(book, imageId);
	}

	

	public List<CategoryDto> loadCategories() {
		return categoryDao.findAll().stream().map(CategoryMapper::categoryToCategoryDto).collect(Collectors.toList());
	}

	
	public ImageDataDto loadImage(int imageId) {
		ImageData image = imageDataDao.findOneById(imageId);
		return ImageDataMapper.imageDataToImageDataDto(image);
	}

	
	public Integer getImageIdByBook(int bookId) {
		String targetId = Integer.toString(bookId);
		return imageDataDao.findOneByEntity("book", targetId);
	}
	
}



