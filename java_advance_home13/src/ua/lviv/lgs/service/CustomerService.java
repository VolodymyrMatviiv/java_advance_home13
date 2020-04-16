package ua.lviv.lgs.service;

import ua.lviv.lgs.domain.Customer;
import ua.lviv.lgs.shared.AbstractCRUD;

public interface CustomerService extends AbstractCRUD<Customer> {
	
	Customer getCustomerByEmail(String email);

}
