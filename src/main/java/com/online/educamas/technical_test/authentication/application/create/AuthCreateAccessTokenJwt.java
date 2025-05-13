package com.online.educamas.technical_test.authentication.application.create;

import java.util.List;

@FunctionalInterface
public interface AuthCreateAccessTokenJwt {

	String createJWtToken(final String email, final List<String> roles);
}
