package com.dao;

import java.util.Date;
import java.util.List;

import com.dto.Customer;
import com.exception.BusinessException;

public interface CustomerDAO {
	public boolean isValidUser(Customer customer) throws BusinessException;

	public Customer getDetailsById(String id) throws BusinessException;

	public Customer getDetailsByContact(long contact) throws BusinessException;

	public List<Customer> getDetailsByName(String name) throws BusinessException;

	public List<Customer> getDetailsByCity(String city) throws BusinessException;

}
