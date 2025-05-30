package com.online.educamas.technical_test.authentication.infrastructure;



import com.online.educamas.technical_test.authentication.domain.AuthRefreshTokenRepository;
import com.online.educamas.technical_test.authentication.domain.RefreshToken;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class AuthRefreshTokenInMemoryRepository implements AuthRefreshTokenRepository {

	private final List<RefreshToken> refreshTokens = new ArrayList<>();

	@Override
	public Optional<RefreshToken> findByToken(String token) {
		return this.refreshTokens.stream().filter(refreshToken -> Objects.equals(refreshToken.getToken(), token))
				.findFirst();
	}

	@Override
	public void save(RefreshToken refreshToken) {
		this.refreshTokens.add(refreshToken);
	}

	@Override
	public void deleteAllExpiredTokens(OffsetDateTime currentDateTime) {
		this.refreshTokens.removeIf(refreshToken -> refreshToken.getExpiryTime().isBefore(currentDateTime));
	}
}
