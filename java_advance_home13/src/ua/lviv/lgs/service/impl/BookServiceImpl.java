package ua.lviv.lgs.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import ua.lviv.lgs.dao.BookDao;
import ua.lviv.lgs.dao.impl.BookDaoImpl;
import ua.lviv.lgs.domain.Book;
import ua.lviv.lgs.service.BookService;

public class BookServiceImpl implements BookService {
	
	private static BookService bookServiceImpl;
	
	private BookDao bookDao;
	
	private BookServiceImpl() {
		bookDao = new BookDaoImpl();
	}
	
	public static BookService getBookService() {
		if (bookServiceImpl == null) {
			bookServiceImpl = new BookServiceImpl();
		}
		return bookServiceImpl;
	} 

	@Override
	public Book create(Book t) {

		return bookDao.create(t);
	}

	@Override
	public Book read(Integer id) {
		return bookDao.read(id);
	}

	@Override
	public Book update(Book t) {
		return bookDao.update(t);
	}

	@Override
	public void delete(Integer id) {
		bookDao.delete(id);
		
	}

	@Override
	public List<Book> raedAll() {
		return bookDao.raedAll();
	}

	@Override
	public Map<Integer, Book> readAllMap() {
		return raedAll().stream().collect(Collectors.toMap(Book::getId, Function.identity()));
	}

	
}
