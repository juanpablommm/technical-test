package com.online.educamas.technical_test.users.infrastructure;


import com.online.educamas.technical_test.users.domain.UserDomain;
import com.online.educamas.technical_test.users.domain.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@Primary
@Component
@AllArgsConstructor
public class UserRepositoryJpaPostgres implements UserRepository {

	private final UserRepositoryJpa userRepositoryJpa;


	private final UserMapper userMapper;

	@Override
	public Optional<UserDomain> findByEmail(final String email) {
		Optional<UserEntity> userEntityOptional = this.userRepositoryJpa.findByEmailEqualsIgnoreCase(email);
		return userEntityOptional.map(this.userMapper::toDomain);
	}


	@Override
	public OptionalLong findUserIdByEmail(final String email) {
		return OptionalLong.of(this.userRepositoryJpa.findUserIdByEmail(email));
	}

	@Transactional
	@Override
	public void save(final UserDomain user) {
		final UserEntity userEntity = this.userMapper.toEntity(user);
		this.userRepositoryJpa.save(userEntity);
	}

	@Transactional
	@Override
	public void delete(String username) {
		this.userRepositoryJpa.deleteByEmailIgnoreCase(username);
	}

	@Override
	public List<UserDomain> findAll(final int page, final int limit) {
		final Page<UserEntity> userEntityPage = this.userRepositoryJpa.findAll(PageRequest.of(page, limit));
		return userEntityPage.getContent().stream().map(this.userMapper::toDomain).toList();
	}
}
