package ua.lviv.lgs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import ua.lviv.lgs.dao.BookDao;
import ua.lviv.lgs.domain.Book;
import ua.lviv.lgs.shared.FactoryManager;
import ua.lviv.lgs.utils.ConnectionUtile;

public class BookDaoImpl implements BookDao {
	
	private EntityManager em = FactoryManager.getEntityManager();

	@Override
	public Book create(Book product) {
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			em.persist(product);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return product;
	}

	@Override
	public Book read(Integer id) {
		Book product = null;
		try {
			product = em.find(Book.class, id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public Book update(Book product) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public void delete(Integer id) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Book> raedAll() {
		Query query = null;
		try {
			query = em.createQuery("SELECT e FROM Book e ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (List<Book>) query.getResultList();
	}

	
}

