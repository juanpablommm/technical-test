package com.online.educamas.technical_test.authentication.application.refresh;



import com.online.educamas.technical_test.authentication.domain.AuthRefreshTokenRepository;
import com.online.educamas.technical_test.authentication.domain.RefreshToken;
import com.online.educamas.technical_test.share.infrastructure.ApplicationException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Component
public class AuthRefreshCommandHandler {

	private final AuthRefreshTokenRepository refreshTokenRepository;

	public AuthRefreshCommandHandler(AuthRefreshTokenRepository refreshTokenRepository) {
		this.refreshTokenRepository = refreshTokenRepository;
	}

	@Transactional(dontRollbackOn = ApplicationException.class)
	public RefreshToken handler(final String refreshToken) {
		Optional<RefreshToken> refreshTokenOptional = this.refreshTokenRepository.findByToken(refreshToken);
		if (refreshTokenOptional.isEmpty())
			throw new ApplicationException("No authentication found for the user", HttpStatus.FORBIDDEN);

		final RefreshToken refreshTokenFound = refreshTokenOptional.get();
		this.validateExpiryTimeRefreshToken(refreshTokenFound);
		return refreshTokenFound;
	}

	private void validateExpiryTimeRefreshToken(RefreshToken refreshTokenFound) {
		final OffsetDateTime timeNow = OffsetDateTime.now(ZoneId.of("America/Bogota"));
		if (refreshTokenFound.getExpiryTime().isBefore(timeNow)) {
			this.refreshTokenRepository.deleteAllExpiredTokens(timeNow);
			throw new ApplicationException("The refresh token has expired", HttpStatus.FORBIDDEN);
		}
	}
}
