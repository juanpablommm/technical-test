package com.online.educamas.technical_test.users.application.delete;


import com.online.educamas.technical_test.users.domain.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class DeleteCommandHandler {

	private final UserRepository userRepository;

	public DeleteCommandHandler(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public void handler(final String username) {
		this.userRepository.delete(username);
	}
}
