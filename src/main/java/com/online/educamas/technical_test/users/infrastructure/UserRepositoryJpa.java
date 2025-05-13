package com.online.educamas.technical_test.users.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByEmailEqualsIgnoreCase(String email);

	@Query(value = "SELECT user.id FROM UserEntity user WHERE user.email = :email")
	Long findUserIdByEmail(String email);


	void deleteByEmailIgnoreCase(String username);
}
