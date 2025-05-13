package com.online.educamas.technical_test.config.infrastructure;

import com.challenge.ecommerce.tps.encript.KeyRsaSupplier;
import com.challenge.ecommerce.tps.jwt.JwtManagement;
import com.online.educamas.technical_test.authentication.application.create.AuthWithPasswordAndEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Configuration
public class ApplicationConfig {

	@Value("${settings-jks.path}")
	private String pathJks;

	@Value("${settings-jks.password}")
	private String passwordJks;

	@Value("${settings-jks.alias}")
	private String aliasJks;

	@Value("${settings-refresh-token.times.jwt}")
	private Long expiryTimeAtMinutes;

	@Bean
	public AuthWithPasswordAndEmail authWithPasswordAndEmail(final AuthenticationManager authenticationManager) {
		return (email, password) -> {
			final Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(email, password));
			return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
		};
	}

	@Bean
	public KeyRsaSupplier keyRsaSupplier()
			throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
		return new KeyRsaSupplier(this.passwordJks, this.aliasJks, this.pathJks);
	}

	@Primary
	@Bean
	public JwtManagement jwtManagement(final KeyRsaSupplier keyRsaSupplier) {
		return new JwtManagement(keyRsaSupplier, this.expiryTimeAtMinutes);
	}

}