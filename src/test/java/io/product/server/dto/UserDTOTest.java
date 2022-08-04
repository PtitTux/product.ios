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
		assertThat(violations).size().isEqualTo(1);
		assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("email");
	}

	@Test
	void compareTwoSameUsersDTO() {
		UUID id = UUID.randomUUID();

		UserDTO dto1 = new UserDTO();
		dto1.setId(id);
		dto1.setName("name");
		dto1.setEmail("test");

		UserDTO dto2 = new UserDTO();
		dto2.setId(id);
		dto2.setName("name");
		dto2.setEmail("test");

		assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
		assertThat(dto1.toString()).isEqualTo(dto2.toString());
	}

	@Test
	void compareTwoUsersWithNameDiffDTO() {
		UUID id = UUID.randomUUID();

		UserDTO dto1 = new UserDTO();
		dto1.setId(id);
		dto1.setName("name");
		dto1.setEmail("test");

		UserDTO dto2 = new UserDTO();
		dto2.setId(id);
		dto2.setName("name2");
		dto2.setEmail("test");

		assertThat(dto1.hashCode()).isNotEqualTo(dto2.hashCode());
		assertThat(dto1.toString()).isNotEqualTo(dto2.toString());
	}

	@Test
	void compareTwoUsersWithEmailDiffDTO() {
		UUID id = UUID.randomUUID();

		UserDTO dto1 = new UserDTO();
		dto1.setId(id);
		dto1.setName("name");
		dto1.setEmail("test");

		UserDTO dto2 = new UserDTO();
		dto2.setId(id);
		dto2.setName("name");
		dto2.setEmail("test2");

		assertThat(dto1.hashCode()).isNotEqualTo(dto2.hashCode());
		assertThat(dto1.toString()).isNotEqualTo(dto2.toString());
	}

	@Test
	void compareTwoUsersWithIdDiffDTO() {
		UUID id = UUID.randomUUID();

		UserDTO dto1 = new UserDTO();
		dto1.setId(id);
		dto1.setName("name");
		dto1.setEmail("test");

		UserDTO dto2 = new UserDTO();
		dto2.setId(UUID.randomUUID());
		dto2.setName("name");
		dto2.setEmail("test");

		assertThat(dto1.hashCode()).isNotEqualTo(dto2.hashCode());
		assertThat(dto1.toString()).isNotEqualTo(dto2.toString());
	}
}
