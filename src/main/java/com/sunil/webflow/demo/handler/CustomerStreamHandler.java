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
public class CustomerStreamHandler {

	@Autowired
	private CustomerDao customerDao;
	
	public Mono<ServerResponse> getRouterStreamCustomers(ServerRequest serverRequest) {
		
		Flux<Customer> customerFlux = customerDao.getReactiveCustomers();
		
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(customerFlux, Customer.class);
	}
}
