package com.online.educamas.technical_test.users.infrastructure;



import com.online.educamas.technical_test.users.domain.UserDomain;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public final class UserMapper {

	private final PasswordEncoder passwordEncoder;

	public UserEntity toEntity(UserDomain user) {
		return UserEntity.builder()
				.email(user.email())
				.firtsName(user.firstName())
				.lastName(user.lastName())
				.password(this.passwordEncoder(user.password()))
				.gender(GenderEnum.valueOf(user.gender()))
				.build();
	}

	public UserDomain toDomain(UserEntity userEntity) {
		return new UserDomain(userEntity.getFirtsName(), userEntity.getLastName(),
				userEntity.getPassword(), userEntity.getEmail(), userEntity.getGender().getGender());
	}



	private String passwordEncoder(final String password) {
		if (Objects.isNull(password) || password.matches("^\\$2[ayb]\\$\\d{2}\\$[./A-Za-z0-9]{53}$"))
			return password;
		return this.passwordEncoder.encode(password);
	}
}
