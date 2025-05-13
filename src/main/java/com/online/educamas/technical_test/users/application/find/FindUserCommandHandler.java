package com.online.educamas.technical_test.users.application.find;



import com.online.educamas.technical_test.share.infrastructure.ApplicationException;
import com.online.educamas.technical_test.users.domain.UserDomain;
import com.online.educamas.technical_test.users.domain.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class FindUserCommandHandler {

	private final UserRepository userRepository;

	public FindUserCommandHandler(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserDomain handler(final String username) {
		return this.userRepository.findByEmail(username)
				.orElseThrow(() -> new ApplicationException("User not found", HttpStatus.NOT_FOUND));
	}
}
