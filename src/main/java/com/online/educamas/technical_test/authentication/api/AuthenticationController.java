package com.online.educamas.technical_test.authentication.api;


import com.online.educamas.technical_test.authentication.api.refresh.AuthRefreshTokenRequestDTO;
import com.online.educamas.technical_test.authentication.application.create.AuthCreateCommandHandler;
import com.online.educamas.technical_test.authentication.application.dto.AuthCreateRequestDto;
import com.online.educamas.technical_test.authentication.application.dto.AuthResponseDto;
import com.online.educamas.technical_test.authentication.application.refresh.AuthRefreshCommandHandler;
import com.online.educamas.technical_test.authentication.domain.RefreshToken;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@AllArgsConstructor
public class AuthenticationController {

	private final AuthCreateCommandHandler authCreateCommandHandler;
	private final AuthRefreshCommandHandler authRefreshCommandHandler;

	@PostMapping(path = "/login")
	public ResponseEntity<AuthResponseDto> createAuthenticate(
			@Valid @RequestBody final AuthCreateRequestDto authRequestDto) {
		final RefreshToken refreshToken = this.authCreateCommandHandler.handler(authRequestDto.email(),
				authRequestDto.password());
		return ResponseEntity.ok(RefreshTokenMapper.toAuthResponseDto(refreshToken));
	}

	@PostMapping(path = "/refresh")
	public ResponseEntity<AuthResponseDto> refreshAuthenticate(
			@RequestBody AuthRefreshTokenRequestDTO authRefreshTokenRequestDTO) {
		final RefreshToken refreshToken = this.authRefreshCommandHandler.handler(authRefreshTokenRequestDTO.token());
		return ResponseEntity.ok(RefreshTokenMapper.toAuthResponseDto(refreshToken));
	}

}
