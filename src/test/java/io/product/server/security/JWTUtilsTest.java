package io.product.server.security;

import io.product.server.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class JWTUtilsTest
{
	@Mock
	JWTUtils utils;

	UserEntity userTest;

	@BeforeEach
	void setUp()
	{
		utils = new JWTUtils();
		ReflectionTestUtils.setField(utils, "jwtSecret", "MyJwTSecret2022");
		ReflectionTestUtils.setField(utils, "jwtExpirationMs", 86400000);

		userTest = UserEntity.builder().name("name").email("email").status(true).password("password").build();
	}

	@Test
	void generateToken()
	{
		String token = utils.generateToken(userTest);
		assertThat(token).isNotNull();
	}

	@Test
	void validateToken() {
		String token = utils.generateToken(userTest);

		Optional<String> body = utils.validateToken(token);
		assertThat(body).contains("email");
	}

	@Test
	void unValidateToken() {
		Optional<String> claims = utils.validateToken("test");
		assertThat(claims).isEmpty();
	}
}