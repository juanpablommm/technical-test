package com.online.educamas.technical_test.users.application.create;


import com.online.educamas.technical_test.users.domain.UserDomain;
import com.online.educamas.technical_test.users.domain.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommandHandler {

	private final UserRepository userRepository;

	public CreateUserCommandHandler(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public void handler(UserDomain userDomain) {
		this.userRepository.save(userDomain);
	}
}
