package org.onbrains.springlerning.customer.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Customer {

	@Setter
	private Long id;
	private String firstName;
	private String secondName;
	private String lastName;
	private String phone;
	private String email;

	/**
	 * @return <b>true</b> в случае если объект является новым(не сохранённым в БД).
	 */
	public boolean isNew() {
		return id == null;
	}

}