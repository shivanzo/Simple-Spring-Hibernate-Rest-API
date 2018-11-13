package com.shivan.app.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivan.app.rest.entity.Customer;
import com.shivan.app.rest.exception.CustomerNotFoundException;
import com.shivan.app.rest.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;

	// get all customers
	@GetMapping("/customers")
	public List<Customer> getCustomers() {

		return customerService.getCustomers();
	}

	// add mapping for GET specific customer
	@GetMapping("/customer/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {

		Customer theCustomer = customerService.getCustomer(customerId);

		if (theCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - "
					+ customerId);
		}
		return theCustomer;
	}

	// add mapping for POST / Customer -- add new Customer
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {

		theCustomer.setId(0);
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}

	// add mapping for PUT / customers - update existing customer

	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {

		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	// add mapping for deleteing the customer
	@DeleteMapping("/customer/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		Customer tempCustomer = customerService.getCustomer(customerId);
		if (tempCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - "
					+ customerId);
		}
		customerService.deleteCustomer(customerId);
		
		return "Delete customer id -  " + customerId;
	}
	
	
	

}
