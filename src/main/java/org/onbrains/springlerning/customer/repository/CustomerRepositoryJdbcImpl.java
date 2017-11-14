package org.onbrains.springlerning.customer.repository;

import static org.onbrains.springlerning.customer.repository.CustomerRepositoryJdbcImpl.SqlParameter.email;
import static org.onbrains.springlerning.customer.repository.CustomerRepositoryJdbcImpl.SqlParameter.firstName;
import static org.onbrains.springlerning.customer.repository.CustomerRepositoryJdbcImpl.SqlParameter.id;
import static org.onbrains.springlerning.customer.repository.CustomerRepositoryJdbcImpl.SqlParameter.lastName;
import static org.onbrains.springlerning.customer.repository.CustomerRepositoryJdbcImpl.SqlParameter.phone;
import static org.onbrains.springlerning.customer.repository.CustomerRepositoryJdbcImpl.SqlParameter.secondName;

import java.util.List;

import javax.sql.DataSource;

import org.onbrains.springlerning.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

/**
 * Реализация репозитория для работы с клиентами через Spring JDBC.
 */
@Repository
public class CustomerRepositoryJdbcImpl implements CustomerRepository {

	private static final String SCHEMA = "springlearning";
	private static final String TABLE_NAME = "customer";
	private static final String[] GENERATED_KEY_COLUMNS = { "id" };

	private static final BeanPropertyRowMapper<Customer> MAPPER = BeanPropertyRowMapper.newInstance(Customer.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private SimpleJdbcInsert insertCustomer;

	@Autowired
	public CustomerRepositoryJdbcImpl(DataSource dataSource) {
		this.insertCustomer = createInsert(dataSource);
	}

	public Customer save(Customer customer) {
		SqlParameterSource map = createMapSqlParameterSource(customer);

		if (customer.isNew()) {
			Number key = insertCustomer.executeAndReturnKey(map);
			customer.setId(key.longValue());
		} else {
			update(map);
		}

		return customer;
	}

	public Customer find(Long id) {
		List<Customer> customers = jdbcTemplate.query("SELECT * FROM springlearning.customer WHERE id = ?", MAPPER, id);
		return DataAccessUtils.singleResult(customers);
	}

	public List<Customer> findAll() {
		return jdbcTemplate.query("SELECT * FROM springlearning.customer", MAPPER);
	}

	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM springlearning.customer WHERE id = ?", id);
	}

	private void update(SqlParameterSource parameterSource) {
		//@formatter:off
		namedParameterJdbcTemplate.update(
			"UPDATE springlearning.customer " +
					"SET" +
					"  first_name  = :" + firstName + "," +
					"  second_name = :" + secondName + "," +
					"  last_name   = :" + lastName + "," +
					"  phone       = :" + phone + "," +
					"  email       = :" + email + " " +
					"WHERE id = :" + id,
				parameterSource);
		//@formatter:on
	}

	private SimpleJdbcInsert createInsert(DataSource dataSource) {
		//@formatter:off
		return new SimpleJdbcInsert(dataSource)
				.withSchemaName(SCHEMA)
				.withTableName(TABLE_NAME)
				.usingGeneratedKeyColumns(GENERATED_KEY_COLUMNS);
		//@formatter:on
	}

	private SqlParameterSource createMapSqlParameterSource(Customer customer) {
		//@formatter:off
		return new MapSqlParameterSource()
				.addValue(id.toString(), customer.getId())
				.addValue(firstName.toString(), customer.getFirstName())
				.addValue(secondName.toString(), customer.getSecondName())
				.addValue(lastName.toString(), customer.getLastName())
				.addValue(phone.toString(), customer.getPhone())
				.addValue(email.toString(), customer.getEmail());
		//@formatter:on
	}

	enum SqlParameter {
		id, firstName, secondName, lastName, phone, email
	}

}