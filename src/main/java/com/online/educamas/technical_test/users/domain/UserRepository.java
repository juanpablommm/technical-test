package com.online.educamas.technical_test.users.domain;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

public interface UserRepository {

	Optional<UserDomain> findByEmail(final String email);


	OptionalLong findUserIdByEmail(final String email);

	void save(final UserDomain user);

	void delete(final String username);

	List<UserDomain> findAll(final int page, final int limit);
}
