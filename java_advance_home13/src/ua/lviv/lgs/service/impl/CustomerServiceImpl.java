package ua.lviv.lgs.service.impl;

import java.util.List;

import ua.lviv.lgs.dao.CustomerDao;
import ua.lviv.lgs.dao.impl.CustomerDaoImpl;
import ua.lviv.lgs.domain.Customer;
import ua.lviv.lgs.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	
	private static CustomerService customerServiceImpl;

	private CustomerDao custumerDao;

	public CustomerServiceImpl() {

			custumerDao = new CustomerDaoImpl();
		
	}

	public static CustomerService getCustomerService() {
		if (customerServiceImpl == null) {
			customerServiceImpl = new CustomerServiceImpl();
		}

		return customerServiceImpl;

	}

	@Override
	public Customer create(Customer t) {

		return custumerDao.create(t);
	}

	@Override
	public Customer read(Integer id) {

		return custumerDao.read(id);
	}

	@Override
	public Customer update(Customer t) {

		return custumerDao.update(t);
	}

	@Override
	public void delete(Integer id) {
		custumerDao.delete(id);

	}

	@Override
	public List<Customer> raedAll() {

		return custumerDao.raedAll();
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		
		return custumerDao.getCustomerByEmail(email);
	}

}