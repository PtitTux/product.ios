package io.product.server.controllers;

import io.product.server.controllers.resources.CreateTrackerResource;
import io.product.server.exceptions.TrackerExistException;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class TrackerControllerTest
{
	@Autowired
	TrackerController controller;

	@Test
	void testFindAll()
	{
		ResponseEntity<Object> response = controller.findAll();

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.hasBody()).isTrue();
	}

	@Test
	void testCreateTracker() {
		CreateTrackerResource t = CreateTrackerResource.builder().name("testing").defaultTracker(false).build();

		try
		{
			ResponseEntity<Object> response = controller.createTracker(t);
			assertThat(response).isNotNull();
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
			assertThat(response.hasBody()).isTrue();
		}
		catch (TrackerExistException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Test
	void testCreateTrackerWithoutName() {
		CreateTrackerResource t = CreateTrackerResource.builder().defaultTracker(false).build();

		assertThatThrownBy(() -> controller.createTracker(t)).isInstanceOf(Exception.class);
	}
}
