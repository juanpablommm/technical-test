package com.online.educamas.technical_test.share.infrastructure;


import com.challenge.ecommerce.tps.exceptions.JwtManagementException;
import com.challenge.ecommerce.tps.exceptions.SecurityLibraryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.KeyManagementException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ApplicationControllerAdvice extends ResponseEntityExceptionHandler {


	@ExceptionHandler(JwtManagementException.class)
	public final ResponseEntity<String> handlerJwtManagementException(JwtManagementException exception) {
		log.error("Internal error - authentication: {}", exception.getMessage());
		return ResponseEntity.internalServerError().body("Internal error - authentication");
	}

	@ExceptionHandler(KeyManagementException.class)
	public final ResponseEntity<String> handlerKeyManagementException(KeyManagementException exception) {
		log.error("Internal error - authentication keys: {}", exception.getMessage());
		return ResponseEntity.internalServerError().body("Internal error - authentication keys");
	}

	@ExceptionHandler(SecurityLibraryException.class)
	public final ResponseEntity<String> handlerSecurityLibraryException(SecurityLibraryException exception) {
		log.error("Internal error - security library: {}", exception.getMessage());
		return ResponseEntity.internalServerError().body("Internal error - security library");
	}

	@ExceptionHandler(ApplicationException.class)
	public final ResponseEntity<String> handlerHospitalException(ApplicationException exception) {
		log.error("Error: {}", exception.getMessage());
		return ResponseEntity.status(exception.getStatusCode()).body(exception.getMessage());
	}


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		final Map<Object, String> message = ex.getBindingResult().getFieldErrors().stream().map(x -> x)
				.collect(Collectors.toMap(FieldError::getRejectedValue, FieldError::getDefaultMessage));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
		final Pattern pattern = Pattern.compile("Key \\((.*)\\)=\\((.*)\\) already exists");
		final Matcher matcher = pattern.matcher(ex.getMessage());
		if (matcher.find()) {
			final String row = matcher.group(1);
			final String valor = matcher.group(2);
			log.error("Duplicate Record Error - Validate the Data {}: {}", row, valor);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate Record Error - Validate the Data");
	}
}
