package com.online.educamas.technical_test.users.domain;

import java.util.Objects;

public record UserDomain(String firstName, String lastName, String password, String email, String gender) {

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		UserDomain that = (UserDomain) o;
		return Objects.equals(email, that.email) && Objects.equals(gender, that.gender) && Objects.equals(lastName, that.lastName) && Objects.equals(password, that.password) && Objects.equals(firstName, that.firstName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, password, email, gender);
	}
}
