package ua.lviv.lgs.service;

import java.util.Map;

import ua.lviv.lgs.domain.Book;
import ua.lviv.lgs.shared.AbstractCRUD;

public interface BookService extends AbstractCRUD<Book> {
	
	public Map<Integer, Book> readAllMap();

}
