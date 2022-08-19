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

class TrackerTest
{
	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void buildTrackerDTO() {
		UUID id = UUID.randomUUID();

		Tracker dto = new Tracker();
		dto.setId(id);
		dto.setName("name");
		dto.setDescription("description");
		dto.setDefaultTracker(true);


		assertThat(dto.getId()).isEqualTo(id);
		assertThat(dto.getName()).isEqualTo("name");
		assertThat(dto.getDescription()).isEqualTo("description");
		assertThat(dto.isDefaultTracker()).isTrue();
	}

	@Test
	void buildTrackerDTOWithNoName() {
		UUID id = UUID.randomUUID();

		Tracker dto = new Tracker();
		dto.setId(id);
		dto.setDescription("description");
		dto.setDefaultTracker(true);

		Set<ConstraintViolation<Tracker>> violations = validator.validate(dto);
		assertThat(violations).size().isEqualTo(1);
		assertThat(violations.iterator().next().getPropertyPath()).hasToString("name");
	}
}
