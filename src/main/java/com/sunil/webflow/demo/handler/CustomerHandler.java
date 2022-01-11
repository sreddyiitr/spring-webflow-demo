package com.sunil.webflow.demo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.sunil.webflow.demo.dao.CustomerDao;
import com.sunil.webflow.demo.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

	@Autowired
	private CustomerDao customerDao;

	public Mono<ServerResponse> getRouterCustomers(ServerRequest serverRequest) {

		Flux<Customer> customerFlux = customerDao.getRouterCustomers();

		return ServerResponse.ok().body(customerFlux, Customer.class);
	}

	public Mono<ServerResponse> getRouterCustomersById(ServerRequest serverRequest) {

		// Get the path parameter
		int customerId = Integer.valueOf(serverRequest.pathVariable("customerId"));

		Flux<Customer> customerFlux = customerDao.getRouterCustomers();

		// customerFlux.filter(customer -> customer.getCustomerId() ==
		// customerId).take(1).single();
		Mono<Customer> customerMono = customerFlux.filter(customer -> customer.getCustomerId() == customerId).next();

		return ServerResponse.ok().body(customerMono, Customer.class);
	}

	public Mono<ServerResponse> saveRouterCustomer(ServerRequest serverRequest) {

		// Get body
		Mono<Customer> customerMono = serverRequest.bodyToMono(Customer.class);
		// Save it in a string for printing/returning response
		Mono<String> stringMono = customerMono
				.map(customerModel -> customerModel.getCustomerId() + " : " + customerModel.getCustomerName());
		return ServerResponse.ok().body(stringMono, String.class);

	}

}
