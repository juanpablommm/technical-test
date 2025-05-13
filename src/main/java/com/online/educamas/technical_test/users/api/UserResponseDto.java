package com.online.educamas.technical_test.users.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserResponseDto {

	private String firtsName;
	private String lastName;
	private String email;
	private Boolean enabled;
	private String gender;
}
