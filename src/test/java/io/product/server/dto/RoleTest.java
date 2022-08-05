package io.product.server.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class RoleTest
{
	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void buildRoleDTO() {
		UUID id = UUID.randomUUID();

		Role dto = new Role();
		dto.setId(id);
		dto.setName("name");

		assertThat(dto.getId()).isEqualTo(id);
		assertThat(dto.getName()).isEqualTo("name");
	}

	@Test
	void buildUserDTOWithWrongName() {
		UUID id = UUID.randomUUID();

		Role dto = new Role();
		dto.setId(id);

		Set<ConstraintViolation<Role>> violations = validator.validate(dto);
		assertThat(violations).size().isEqualTo(1);
		assertThat(violations.iterator().next().getPropertyPath()).hasToString("name");
	}
}
