package com.online.educamas.technical_test.authentication.infrastructure;



import com.online.educamas.technical_test.share.infrastructure.ApplicationException;
import com.online.educamas.technical_test.users.domain.UserDomain;
import com.online.educamas.technical_test.users.domain.UserRepository;
import com.online.educamas.technical_test.users.infrastructure.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		UserDomain user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ApplicationException("User not fount", HttpStatus.NOT_FOUND));

		return this.userMapper.toEntity(user);
	}
}
