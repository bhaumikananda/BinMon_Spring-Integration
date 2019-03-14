package org.binmon.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.binmon.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	Map<Integer, Customer> customerStorage = new HashMap<Integer, Customer>();

	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	@PostConstruct
	public void init() {
		Customer jack = new Customer(1, "Jack", 20);
		Customer peter = new Customer(2, "Peter", 30);

		customerStorage.put(jack.getId(), jack);
		customerStorage.put(peter.getId(), peter);
	}

	public void insert(Customer customer) {
		customerStorage.put(customer.getId(), customer);
		log.info("Customers after POST:");
		for (Map.Entry<Integer, Customer> entry : customerStorage.entrySet()) {
			log.info(entry.getValue().toString());
		}
	}

	public List<Customer> getAll() {

		List<Customer> result = customerStorage.entrySet().stream().map(entry -> entry.getValue())
				.collect(Collectors.toList());

		return result;
	}

	public void delete(int id) {
		try {
			customerStorage.remove(id);
		} catch (Exception e) {
		}

	}

	public void change(Customer newCust) {
		customerStorage.put(newCust.getId(), newCust);
		log.info("Customers after PUT:");
		for (Map.Entry<Integer, Customer> entry : customerStorage.entrySet()) {
			log.info(entry.getValue().toString());
		}
	}
}