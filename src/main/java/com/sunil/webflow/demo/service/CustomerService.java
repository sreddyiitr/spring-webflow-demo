package com.sunil.webflow.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunil.webflow.demo.dao.CustomerDao;
import com.sunil.webflow.demo.model.Customer;

import reactor.core.publisher.Flux;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	public List<Customer> getCustomers() {
		long start = System.currentTimeMillis();
		List<Customer> customers = customerDao.getCustomers();
		long end = System.currentTimeMillis();
		System.out.println("Total Execution Time: " + (end - start));
		return customers;
	}
	
	public Flux<Customer> getReactiveCustomers() {
		long start = System.currentTimeMillis();
		Flux<Customer> customers = customerDao.getReactiveCustomers();
		long end = System.currentTimeMillis();
		System.out.println("Total Execution Time: " + (end - start));
		return customers;
	}
}
