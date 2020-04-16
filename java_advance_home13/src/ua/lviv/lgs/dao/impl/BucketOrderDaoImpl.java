package ua.lviv.lgs.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import ua.lviv.lgs.dao.BucketOrderDao;
import ua.lviv.lgs.domain.BucketOrder;
import ua.lviv.lgs.shared.FactoryManager;
import ua.lviv.lgs.utils.ConnectionUtile;

public class BucketOrderDaoImpl implements BucketOrderDao {

	private EntityManager em = FactoryManager.getEntityManager();

	@Override
	public BucketOrder create(BucketOrder bucketOrder) {
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			em.persist(bucketOrder);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bucketOrder;
	}

	@Override
	public BucketOrder read(Integer id) {
		BucketOrder bucketOrder = null;
		try {
			bucketOrder = em.find(BucketOrder.class, id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bucketOrder;
	}

	@Override
	public BucketOrder update(BucketOrder t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		try {
			BucketOrder bucket = read(id);
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			em.remove(bucket);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<BucketOrder> raedAll() {
		Query query = null;
		try {
			query = em.createQuery("SELECT e FROM BucketOrder e");
			if (!query.getResultList().isEmpty()) {
				return query.getResultList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
