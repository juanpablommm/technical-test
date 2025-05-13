package com.online.educamas.technical_test.authentication.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public interface AuthRefreshTokenJpaRepository extends JpaRepository<RefreshTokenEntity, Long> {

	Optional<RefreshTokenEntity> findByToken(final String token);

	@Modifying
	@Query("DELETE FROM RefreshTokenEntity refreshToken WHERE refreshToken.expiryTime < :currentDateTime")
	void deleteAllExpiredTokens(final OffsetDateTime currentDateTime);
}
