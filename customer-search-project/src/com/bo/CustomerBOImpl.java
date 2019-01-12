package com.bo;

import java.util.Date;
import java.util.List;

import com.dao.CustomerDAO;
import com.dao.CustomerDAOImpl;
import com.dto.Customer;
import com.exception.BusinessException;

public class CustomerBOImpl implements CustomerBO {

	@Override
	public boolean isValidUser(Customer customer) throws BusinessException {
		boolean b = false;
		if (customer != null && customer.getCusId() != null && customer.getPassword() != null) {
			if (customer.getCusId().matches("CUS[a-zA-Z]{2,8}[0-9]{5}")
					&& customer.getPassword().matches("[a-zA-Z]{3,7}@[0-9]{3}")) {
				CustomerDAO customerDAO = new CustomerDAOImpl();
				b = customerDAO.isValidUser(customer);
			}
		}
		return b;
	}

	@Override
	public Customer getDetailsById(String id) throws BusinessException {
		Customer customer = null;
		if (id != null) {
			CustomerDAO customerDAO = new CustomerDAOImpl();
			customer = customerDAO.getDetailsById(id);
		}
		return customer;
	}

	@Override
	public Customer getDetailsByContact(long contact) throws BusinessException {
		Customer customer = null;
		String str = "" + contact;
		if (str.length() != 0 && str.length() == 10) {
			CustomerDAO customerDAO = new CustomerDAOImpl();
			customer = customerDAO.getDetailsByContact(contact);
		} else {
			throw new BusinessException("Please provide valid contact number.");
		}
		return customer;
	}

	@Override
	public List<Customer> getDetailsByName(String name) throws BusinessException {
		List<Customer> customerList = null;
		if (name != null) {
			CustomerDAO customerDAO = new CustomerDAOImpl();
			customerList = customerDAO.getDetailsByName(name);
		}
		return customerList;
	}

	@Override
	public List<Customer> getDetailsByCity(String city) throws BusinessException {
		List<Customer> cutomerList = null;
		if (city != null) {
			// DAO
			CustomerDAO customerDAO = new CustomerDAOImpl();
			cutomerList = customerDAO.getDetailsByCity(city);
		}
		return cutomerList;
	}
}
