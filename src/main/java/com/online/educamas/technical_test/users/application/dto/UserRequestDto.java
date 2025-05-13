package com.online.educamas.technical_test.users.application.dto;


import com.online.educamas.technical_test.users.api.validators.ValidEmail;
import com.online.educamas.technical_test.users.api.validators.ValidPassword;

public record UserRequestDto(String firstName, String lastName, @ValidPassword String password,
							 @ValidEmail String email, String gender) {
}
