package ua.lviv.lgs.dao;

import ua.lviv.lgs.domain.Customer;
import ua.lviv.lgs.shared.AbstractCRUD;

public interface CustomerDao extends AbstractCRUD<Customer> {
	
	Customer getCustomerByEmail(String email);

}