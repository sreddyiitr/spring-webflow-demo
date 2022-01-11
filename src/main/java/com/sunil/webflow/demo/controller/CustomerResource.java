package com.sunil.webflow.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunil.webflow.demo.model.Customer;
import com.sunil.webflow.demo.service.CustomerService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/customers")
public class CustomerResource {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}

	@GetMapping(value="/reactive",produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Customer> getReactiveCustomers() {
		
		return customerService.getReactiveCustomers();
	}	
}
