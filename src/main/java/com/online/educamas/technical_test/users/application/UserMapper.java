package com.online.educamas.technical_test.users.application;


import com.online.educamas.technical_test.users.api.UserResponseDto;
import com.online.educamas.technical_test.users.application.dto.UserRequestDto;
import com.online.educamas.technical_test.users.domain.UserDomain;

public final class UserMapper {


	public static UserDomain toDomain(UserRequestDto userRequestDto) {
		return new UserDomain(userRequestDto.firstName(), userRequestDto.lastName(),
				userRequestDto.password(), userRequestDto.email(), userRequestDto.gender());
	}

	public static UserResponseDto toResponseDTO(UserDomain user) {
		return UserResponseDto.builder()
				.firtsName(user.firstName())
				.lastName(user.lastName())
				.email(user.email())
				.gender(user.gender())
				.build();
	}
}
