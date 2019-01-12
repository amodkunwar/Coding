package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dbUtil.OracleDBConnection;
import com.dto.Customer;
import com.exception.BusinessException;

public class CustomerDAOImpl implements CustomerDAO {

	private Connection connection;
	private CallableStatement callableStatment;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	Customer customer;

	public Customer registerDetials(Customer customer) {

		try {
			connection = OracleDBConnection.getConnection();
			String sql = "{call REGISTERCUSTOMER(?,?,?,?,?,?)}";
			callableStatment = connection.prepareCall(sql);
			callableStatment.setString(2, customer.getName());
			callableStatment.setDate(3, new java.sql.Date(customer.getDob().getTime()));
			callableStatment.setLong(4, customer.getContact());
			callableStatment.setString(5, customer.getCity());
			callableStatment.setString(6, customer.getPassword());

			callableStatment.registerOutParameter(1, java.sql.Types.VARCHAR);

			callableStatment.execute();

			customer.setCusId(callableStatment.getString(1));

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return customer;

	}

	@Override
	public boolean isValidUser(Customer customer) throws BusinessException {
		boolean b = false;

		try {
			connection = OracleDBConnection.getConnection();
			String sql = "select name, dob, contact,city from customer where cusid = ? and password = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getCusId());
			preparedStatement.setString(2, customer.getPassword());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				b = true;
			} else {
				throw new BusinessException("Internal errors");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal error");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return b;
	}

	@Override
	public Customer getDetailsById(String id) throws BusinessException {
		customer = new Customer();
		try {
			connection = OracleDBConnection.getConnection();
			String sql = "select name, dob, contact, city from customer where cusid = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				customer.setCusId(id);
				customer.setName(resultSet.getString("name"));
				customer.setDob(resultSet.getDate("dob"));
				customer.setCity(resultSet.getString("city"));
				customer.setContact(resultSet.getLong("contact"));
			} else {
				throw new BusinessException("Internal errors");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public Customer getDetailsByContact(long contact) throws BusinessException {
		customer = new Customer();
		String sql = "select cusid, name, dob, contact,city from customer where contact = ?";
		try {
			connection = OracleDBConnection.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, contact);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				customer.setContact(contact);
				customer.setCusId(resultSet.getString("cusid"));
				customer.setName(resultSet.getString("name"));
				customer.setCity(resultSet.getString("city"));
				customer.setDob(resultSet.getDate("dob"));
			} else {
				throw new BusinessException("Internal errors");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public List<Customer> getDetailsByName(String name) throws BusinessException {
		List<Customer> customerList = new ArrayList<>();
		customer = new Customer();
		try {
			connection = OracleDBConnection.getConnection();
			String sql = "select cusid, city, dob, contact from customer where name = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				customer.setName(name);
				customer.setCity(resultSet.getString("city"));
				customer.setContact(resultSet.getLong("contact"));
				customer.setCusId(resultSet.getString("cusid"));
				customer.setDob(resultSet.getDate("dob"));
				customerList.add(customer);
			}
			for (Customer c : customerList) {
				System.out.println(c);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Customer> getDetailsByCity(String city) throws BusinessException {
		List<Customer> customerList = new ArrayList<>();
		customer = new Customer();

		try {
			connection = OracleDBConnection.getConnection();
			String sql = "select cusid, name, dob, contact from customer where city = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, city);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				customer.setCity(city);
				customer.setCusId(resultSet.getString("cusId"));
				customer.setName(resultSet.getString("name"));
				customer.setDob(resultSet.getDate("dob"));
				customer.setContact(resultSet.getLong("contact"));
				customerList.add(customer);
			}
			for (Customer c : customerList) {
				System.out.println(c);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return customerList;
	}

}
