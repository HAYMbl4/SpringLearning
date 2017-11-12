package org.onbrains.springlerning.customer.repository;

import org.onbrains.springlerning.customer.model.Customer;

import java.util.List;

public interface CustomerRepository {

	Customer save(Customer customer);

	Customer find(Long id);

	List<Customer> findAll();

	boolean remove(Long id);

}