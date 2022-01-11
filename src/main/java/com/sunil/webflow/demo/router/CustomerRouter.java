package com.sunil.webflow.demo.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.sunil.webflow.demo.handler.CustomerHandler;
import com.sunil.webflow.demo.handler.CustomerStreamHandler;

/*
 * In Reactive programming, the rest end points are implemented using functional endpoints
 * 
 * Router is the equivalent of Controller
 * Handler is the equivalent of Service
 * 
 */

@Configuration
public class CustomerRouter {
	
	@Autowired
	private CustomerHandler customerHandler;

	@Autowired
	private CustomerStreamHandler customerStreamHandler;
	
	@Bean
	public RouterFunction<ServerResponse> getCustomerDetails() {
		
		return RouterFunctions.route()
				.GET("/router/customers/", customerHandler::getRouterCustomers)
				.GET("/router/stream/customers/",customerStreamHandler::getRouterStreamCustomers)
				.POST("/router/customers/",customerHandler::saveRouterCustomer)
				.GET("/router/customers/{customerId}",customerHandler::getRouterCustomersById)
				.build();
	}

}
