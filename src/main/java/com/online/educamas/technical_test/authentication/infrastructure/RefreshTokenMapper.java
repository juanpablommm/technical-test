package com.online.educamas.technical_test.authentication.infrastructure;

import com.challenge.ecommerce.tps.jwt.JwtManagement;
import com.online.educamas.technical_test.authentication.domain.RefreshToken;
import com.online.educamas.technical_test.users.infrastructure.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RefreshTokenMapper {

	private final JwtManagement jwtManagement;

	public RefreshTokenEntity toJpaEntity(RefreshToken refreshToken) {
		RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.builder()
				.user(UserEntity.builder().id(refreshToken.getUserId()).build())
				.token(refreshToken.getToken())
				.expiryTime(refreshToken.getExpiryTime())
				.build();
		refreshTokenEntity.setUser(UserEntity.builder().id(refreshToken.getUserId()).build());
		return refreshTokenEntity;
	}

	public RefreshToken toDomain(final RefreshTokenEntity refreshTokenEntity) {
		final UserEntity userEntity = refreshTokenEntity.getUser();
		final List<String> roles = userEntity.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
		return new RefreshToken(userEntity.getId(), refreshTokenEntity.getToken(),
				jwtManagement.createToken(userEntity.getEmail(), roles), refreshTokenEntity.getExpiryTime(),
				refreshTokenEntity.getUser().getId());
	}
}
