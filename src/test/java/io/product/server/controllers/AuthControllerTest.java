package io.product.server.controllers;

import io.product.server.controllers.resources.SigninResource;
import io.product.server.controllers.resources.SignupResource;
import io.product.server.exceptions.UserExistException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class AuthControllerTest
{
	@Autowired
	AuthController authController;

	@Test
	void testSignupValid()
	{
		SignupResource signup = SignupResource.builder().email("test-create@product.io").name("Test").password("Password").build();

		try
		{
			ResponseEntity<Object> response = authController.signup(signup);

			assertThat(response).isNotNull();
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
			assertThat(response.hasBody()).isTrue();
		}
		catch (UserExistException e)
		{
			assertThat(e).doesNotThrowAnyException();
		}
	}

	@Test
	void testSignupWithoutEmail()
	{
		SignupResource signup = SignupResource.builder().name("Test").password("Password").build();

		assertThatThrownBy(() -> authController.signup(signup)).isInstanceOf(Exception.class);
	}

	@Test
	void testSignupWithoutName()
	{
		SignupResource signup = SignupResource.builder().email("test-create@product.io").password("Password").build();

		assertThatThrownBy(() -> authController.signup(signup)).isInstanceOf(Exception.class);
	}
	@Test
	void testSignupWithoutPassword()
	{
		SignupResource signup = SignupResource.builder().name("Test").email("test-create@product.io").build();

		assertThatThrownBy(() -> authController.signup(signup)).isInstanceOf(Exception.class);
	}

	@Test
	void testSigninValid()
	{
		SigninResource signin = SigninResource.builder().email("test@product.io").password("manager").build();

		try
		{
			ResponseEntity<Object> response = authController.signin(signin);

			assertThat(response).isNotNull();
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(response.hasBody()).isTrue();
		}
		catch (Exception e)
		{
			assertThat(e).doesNotThrowAnyException();
		}
	}
}
