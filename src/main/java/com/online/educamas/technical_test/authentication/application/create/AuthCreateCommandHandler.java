package com.online.educamas.technical_test.authentication.application.create;


import com.online.educamas.technical_test.authentication.domain.AuthRefreshTokenRepository;
import com.online.educamas.technical_test.authentication.domain.RefreshToken;
import com.online.educamas.technical_test.users.domain.UserRepository;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class AuthCreateCommandHandler {

	private final AuthRefreshTokenRepository refreshTokenRepository;
	private final AuthWithPasswordAndEmail authWithPasswordAndEmail;
	private final AuthCreateAccessTokenJwt authCreateAccessTokenJwt;
	private final UserRepository userRepository;

	public AuthCreateCommandHandler(AuthRefreshTokenRepository refreshTokenRepository, AuthWithPasswordAndEmail authWithPasswordAndEmail, AuthCreateAccessTokenJwt authCreateAccessTokenJwt, UserRepository userRepository) {
		this.refreshTokenRepository = refreshTokenRepository;
		this.authWithPasswordAndEmail = authWithPasswordAndEmail;
		this.authCreateAccessTokenJwt = authCreateAccessTokenJwt;
		this.userRepository = userRepository;
	}

	public RefreshToken handler(final String email, final String password) {
		List<String> roles = this.authWithPasswordAndEmail.authentication(email, password);
		final Long userId = this.userRepository.findUserIdByEmail(email).orElseThrow(
				() -> new RuntimeException("User authentication failed"));
		final String accessToken = this.authCreateAccessTokenJwt.createJWtToken(email, roles);
		final RefreshToken refreshToken = RefreshToken.createToAuth(String.valueOf(UUID.randomUUID()), accessToken,
				OffsetDateTime.now().plusMinutes(10), userId);
		this.refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}
}
