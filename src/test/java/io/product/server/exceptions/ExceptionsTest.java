package io.product.server.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ExceptionsTest
{

	@Test
	void generateAppException()
	{
		assertThat(new AppException("message")).hasMessage("message");
	}

	@Test
	void generateUserExistException()
	{
		assertThat(new UserExistException()).hasMessage("User already exist with this email");
	}
}