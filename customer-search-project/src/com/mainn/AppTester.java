package com.mainn;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.bo.CustomerBO;
import com.bo.CustomerBOImpl;
import com.dao.CustomerDAO;
import com.dao.CustomerDAOImpl;
import com.dto.Customer;
import com.exception.BusinessException;

public class AppTester {
	public static void main(String[] args) throws BusinessException, ParseException {

		Customer customer = new Customer();
		Scanner scan = new Scanner(System.in);
		System.out.println("Select the option from below screen.");
		System.out.println("-----------------------------------------");
		int num = 0;
		do {
			System.out.println("1.) Register");
			System.out.println("2.) Login");
			System.out.println("3.) Get details by ID");
			System.out.println("4.) Get details by contact");
			System.out.println("5.) Get details by Name");
			System.out.println("6.) Get details by City");
			System.out.println("7.) Get details by Date");
			System.out.println("8.) Exit");
			System.out.println("Select the option");
			num = scan.nextInt();

			String dd = "25.02.2018";

			Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dd);

			if (num == 1) {

				// Customer customer1 = new Customer("Abhi", new Date(),
				// 9937043700L, "Chennai", "Abhi@1234");

				System.out.println("Enter the name");
				String name = scan.next();
				customer.setName(name);
				customer.setDob(date);
				System.out.println("Enter the contact number");
				long contact = scan.nextLong();
				customer.setContact(contact);
				System.out.println("Enter the city name");
				String city = scan.next();
				customer.setCity(city);
				System.out.println("Enter the password");
				String password = scan.next();
				customer.setPassword(password);

				// BO
				CustomerDAOImpl customerDAOImpl = new CustomerDAOImpl();
				customer = customerDAOImpl.registerDetials(customer);

				if (customer.getCusId() != null && customer.getPassword() != null) {
					System.out.println("Registration done at " + new Date());
					System.out.println("Your id is " + customer.getCusId());
				} else {
					System.out.println("Please provide valid id or password.");
				}
			}

			else if (num == 2) {
				System.out.println("Enter the id");
				String id = scan.next();
				System.out.println("Enter the password");
				String password = scan.next();
				customer.setCusId(id);
				customer.setPassword(password);

				CustomerDAO customerDAO = new CustomerDAOImpl();

				if (customer.getCusId() != null && customer.getPassword() != null) {
					if (customerDAO.isValidUser(customer)) {
						System.out.println("You logged in successfully at " + new Date());
						System.out.println(customer.getCusId());
					}
				} else {
					throw new BusinessException("please provide the valid id or password");
				}

			}

			else if (num == 3) {
				System.out.println("Enter the Id");
				String cusId = scan.next();
				customer.setCusId(cusId);

				CustomerBO customerBO = new CustomerBOImpl();
				Customer detailsById = customerBO.getDetailsById(cusId);
				if (detailsById != null) {
					System.out.println("Details are");
					System.out.println(detailsById);
				}
			} else if (num == 4) {

				System.out.println("Enter the contact");
				long contact = scan.nextLong();
				customer.setContact(contact);

				CustomerBO customerBO = new CustomerBOImpl();
				Customer detailsByContact = customerBO.getDetailsByContact(contact);

				if (detailsByContact != null) {
					System.out.println("Details");
					System.out.println(detailsByContact);
				} else {
					throw new BusinessException("No contact found");
				}
			} else if (num == 5) {
				System.out.println("Enter the name");
				String name = scan.next();
				customer.setName(name);

				CustomerBO customerBO = new CustomerBOImpl();
				List<Customer> detailsByName = customerBO.getDetailsByName(name);

				System.out.println("Details are ");
				System.out.println(detailsByName);

			}

			else if (num == 6) {
				System.out.println("Enter the city");
				String city = scan.next();
				customer.setCity(city);

				CustomerBO customerBO = new CustomerBOImpl();
				List<Customer> detailsByCity = customerBO.getDetailsByCity(city);

				System.out.println("details are ");
				System.out.println(detailsByCity);
			}

			else if (num == 7) {
				System.out.println("Exit from Application");
				break;
			}
		} while (num != 8);

		System.out.println("Thanking for using our application");
	}

}
