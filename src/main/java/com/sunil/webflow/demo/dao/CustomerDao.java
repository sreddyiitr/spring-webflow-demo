package com.sunil.webflow.demo.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.sunil.webflow.demo.model.Customer;

import reactor.core.publisher.Flux;

@Component
public class CustomerDao {

	public static void sleepExecution(int i) {
	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Customer> getCustomers() {

		return IntStream.rangeClosed(1, 50)
				.peek(i -> System.out.println("Processing Count: " + i))
				//To show the delay
				.peek(CustomerDao::sleepExecution)
				.mapToObj(i -> new Customer(i, "Customer" + i))
				.collect(Collectors.toList());
	}
	
	public Flux<Customer> getReactiveCustomers() {
		return Flux.range(1, 50)
				.doOnNext(i -> System.out.println("Processing Count in Reactive: " + i))
				.delayElements(Duration.ofSeconds(1))
				.map(i -> new Customer(i, "Customer" + i));
	}
	
	public Flux<Customer> getRouterCustomers() {
		return Flux.range(1, 50)
				.doOnNext(i -> System.out.println("Processing Count in Reactive: " + i))
				//.delayElements(Duration.ofSeconds(1))
				.map(i -> new Customer(i, "Customer" + i));
	}
}
