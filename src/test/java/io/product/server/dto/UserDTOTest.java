package io.product.server.dto;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserDTOTest
{
	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void buildUserDTO() {
		UUID id = UUID.randomUUID();

		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setName("name");
		dto.setEmail("test@product.io");

		assertThat(dto.getId()).isEqualTo(id);
		assertThat(dto.getName()).isEqualTo("name");
		assertThat(dto.getEmail()).isEqualTo("test@product.io");
	}

	@Test
	void buildUserDTOWithWrongEmail() {
		UUID id = UUID.randomUUID();

		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setName("name");
		dto.setEmail("test");

		Set<ConstraintViolation<UserDTO>> violations = validator.validate(dto);
		assertThat(violations).isNotEmpty();
		assertThat(violations).size().isEqualTo(1);
		assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("email");
	}
}
