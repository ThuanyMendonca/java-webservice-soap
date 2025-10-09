package com.github.soap.webservice.customers_adminstration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.github.soap.webservice.customers_adminstration.bean.Customer;
import com.github.soap.webservice.customers_adminstration.helper.Status;

@Component
public class CustomerDetailService {
	
	private static List<Customer> customers = new ArrayList<>();
	
	static {
		Customer customer1 = new Customer(1, "Alice", "123123123123", "alice@gmail.com");
		customers.add(customer1);		
		
		Customer customer2 = new Customer(2, "Joao", "99999999999", "joao@gmail.com");
		customers.add(customer2);
		
		Customer customer3 = new Customer(3, "Jose", "888888888888", "jose@gmail.com");
		customers.add(customer3);
		
		Customer customer4 = new Customer(4, "Maria", "7777777777777", "maria@gmail.com");
		customers.add(customer4);
	}
	
	public Customer findById(int id) {
		Optional<Customer> customerOptional = customers.stream().filter(c -> c.getId() == id).findAny();
		if(customerOptional.isPresent()) {
			return customerOptional.get();
		}
		
		return null;
	}
	
	public List<Customer> findAll() {
		return customers;
	}
	
	public Status deleteById(int id) {
		Optional<Customer> customerOptional = customers.stream().filter(c -> c.getId() == id).findAny();
		
		if(customerOptional.isPresent()) {
			customers.remove(customerOptional.get());
			return Status.SUCCESS;
		}
		
		return Status.FAILURE;
		
	}
}
