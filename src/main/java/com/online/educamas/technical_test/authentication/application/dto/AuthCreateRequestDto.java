package com.online.educamas.technical_test.authentication.application.dto;


import com.online.educamas.technical_test.users.api.validators.ValidEmail;
import com.online.educamas.technical_test.users.api.validators.ValidPassword;

public record AuthCreateRequestDto(@ValidEmail String email, @ValidPassword String password) {
}
