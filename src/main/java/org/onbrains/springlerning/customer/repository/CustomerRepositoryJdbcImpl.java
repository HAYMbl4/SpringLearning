package org.onbrains.springlerning.customer.repository;

import org.onbrains.springlerning.customer.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryJdbcImpl implements CustomerRepository {

	public Customer save(Customer customer) {
		return null;
	}

	public Customer find(Long id) {
		return null;
	}

	public List<Customer> findAll() {
		return null;
	}

	public boolean remove(Long id) {
		return false;
	}

}