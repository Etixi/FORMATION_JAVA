package com.infotel.eshop.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotel.eshop.dao.BookDao;
import com.infotel.eshop.dao.ItemCounterDao;
import com.infotel.eshop.dao.OrderDao;
import com.infotel.eshop.dao.OrderItemDao;
import com.infotel.eshop.dao.jdbc.ImageDataDao;
import com.infotel.eshop.dto.BasketDto;
import com.infotel.eshop.dto.BookLightDto;
import com.infotel.eshop.dto.OrderDto;
import com.infotel.eshop.dto.OrderItemDto;
import com.infotel.eshop.exception.OrderException;
import com.infotel.eshop.mapper.BasketMapper;
import com.infotel.eshop.mapper.OrderMapper;
import com.infotel.eshop.mapper.UserMapper;
import com.infotel.eshop.model.Basket;
import com.infotel.eshop.model.BasketItem;
import com.infotel.eshop.model.Book;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.ItemCounter;
import com.infotel.eshop.model.Order;
import com.infotel.eshop.model.OrderItem;
import com.infotel.eshop.model.OrderStatus;

@Service @Transactional
public class OrderServiceImpl implements OrderService{

	
	
	private final static Logger log = LogManager.getLogger(OrderServiceImpl.class);
	@Autowired 
	private OrderDao orderdao; //= new OrderDaoJbdc();
	@Autowired
	private OrderItemDao orderItemDao;// = new OrderItemDaoJdbc();
	@Autowired 
	private ItemCounterDao itemCounterDao;// = new ItemCounterDaoJpa();
	@Autowired 
	private ImageDataDao imageDataDao;//= new ImageDataDaoJpa();
	@Autowired
	private BookDao bookDao;// = new BookDaoJpa();
	
	@Override
	public OrderDto checkoutBasket(BasketDto dto) throws OrderException{
		// panier vide, utilisateur non authentifié
		Basket basket = BasketMapper.basketDtoToBasket(dto);
		Customer cust = UserMapper.customerDtoToCustomer(dto.getCustomer());;
		
		validateBasketAndCustomer(basket, cust);
		
		
		Order order = basketToOrder(basket, cust);
		generateOrderNumber(order);
		saveOrder(order);
		
		if(log.isDebugEnabled()) {
			log.debug("passer la commande : " + order);
		}
		
		return OrderMapper.orderToOrderDto(order);
	}
	
	
	private Order basketToOrder(Basket basket, Customer cust) {
		Order order = new Order();
		
		order.setCreated(LocalDateTime.now().withNano(0));
		order.setStatus(OrderStatus.Allocated);
		//order.setStatus(OrderStatus.Allocated);
		order.setCustomer(cust);
		

		for(BasketItem basketItem: basket.getItems()) {
//			OrderItem orderItem = new OrderItem();
//			orderItem.setBook(basketItem.getBook());
//			orderItem.setQuantity(basketItem.getQuantity());
//			orderItem.setUnitPrice(basketItem.getBook().getPrice().getValue());
//			
//			order.addItem(orderItem);
			Book book = bookDao.findOneById(basketItem.getBook().getId());
			order.addItem(book, basketItem.getQuantity());

		}
		
		return order;
	}
	
	private void validateBasketAndCustomer(Basket basket, Customer cust) throws OrderException{
	if(basket==null || basket.getItems() == null || basket.getItems().isEmpty()) {
			throw new OrderException("Impossible de passer la commande: le panier est vide");
		}
	if (cust == null) {
		throw new OrderException("Impossible de passer la commande: l'utilistateur n'est pas authentifié");
	}
	}
	
	private void generateOrderNumber(Order order) {
		StringBuilder sb = new StringBuilder();
		sb.append("CDE");
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yy");
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yy");
		sb.append(sdf.format(order.getCreated())).append("-");
		
		int count = getNextCounterValue(order); //5;
		NumberFormat countFmt = new DecimalFormat("0000");
		sb.append(countFmt.format(count));
		String number = sb.toString();
		
		order.setNumber(number);
	}
	
	
	private int getNextCounterValue(Order order) {
		
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yy");
		String  key = "ORDER-" + sdf.format(order.getCreated());
		
		
		
		ItemCounter counter = itemCounterDao.findOneByItem(key);
		if(counter == null) {
			//Initialiser compteur
			counter = new ItemCounter();
			counter.setItem(key);	
			itemCounterDao.create(counter);
		}
		counter.increment();
		itemCounterDao.update(counter);
		
		
		return counter.getValue();	
	}
	
	
	private void saveOrder(Order order) {
		orderdao.create(order);//create(order);
		for(OrderItem orderItem: order.getItems()) {
			orderItemDao.create(orderItem);
		}
	}

	@Override
	public List<OrderDto> getOrdersByCustomer(String username) {
		
		List<Order> orders = orderdao.findManyByCustomer(username);
//		
//		for(Order order: orders) {
//			
//			List<OrderItem> items = orderItemDao.findManyByByOrder(order.getId());
//
//			
//			for(OrderItem item: items) {
//			     order.addItem(item);
//			}
//			if (items.isEmpty()) order.setItems(new ArrayList<>());
//		}
		return orders.stream()
				.map(o -> OrderMapper.orderToOrderDto(o))
				.peek(o -> populatImage(o))
				.collect(Collectors.toList());
		
	}
	
	private void populatImage(OrderDto order) {
		for (OrderItemDto item : order.getItems()) {
			BookLightDto book = item.getBook();
			book.setImageId(getImageIdByBook(book.getId()));
		}
	}
	private Integer getImageIdByBook(int bookId) {
		String targetId = Integer.toString(bookId);
		return imageDataDao.findOneByEntity("book", targetId);
	}
	
	
	@Override
	public List<OrderDto> getOrdersToProcess() {
		return orderdao.findManyByStatus(OrderStatus.Allocated, OrderStatus.Packed)
					.stream()
					.map(OrderMapper::orderToOrderDto)
					.toList();
	}
}
	
	
	


