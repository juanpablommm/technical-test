package com.online.educamas.technical_test.authentication.api;


import com.online.educamas.technical_test.authentication.application.dto.AuthResponseDto;
import com.online.educamas.technical_test.authentication.domain.RefreshToken;

public final class RefreshTokenMapper {

	public static AuthResponseDto toAuthResponseDto(RefreshToken refreshToken) {
		return AuthResponseDto.builder()
				.token(refreshToken.getToken())
				.accessToken(refreshToken.getAccessToken())
				.build();
	}
}
