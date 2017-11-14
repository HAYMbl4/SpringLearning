package org.onbrains.springlerning.customer.repository;

import java.util.List;

import org.onbrains.springlerning.customer.model.Customer;

public interface CustomerRepository {

	Customer save(Customer customer);

	Customer find(Long id);

	List<Customer> findAll();

	void delete(Long id);

}