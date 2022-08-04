package io.product.server.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

public class APIResponseTest
{

	@Test
	void checkOnlyMessage() {
		APIResponse response = new APIResponse("testing message");

		assertThat(response.getMessage()).isEqualTo("testing message");
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getData()).isNull();
	}

	@Test
	void checkBuildSuccessMessage() {
		ResponseEntity<Object> response = new APIResponse("testing message").build();

		assertThat(response.hasBody()).isTrue();
		assertThat(response.getBody()).isNotNull();

		if(response.getBody() instanceof Map)
		{
			Map<String, Object> body = (Map<String, Object>) response.getBody();
			assertThat(body.containsKey("message")).isTrue();
			assertThat(body.containsKey("status")).isTrue();
			assertThat(body.containsKey("data")).isFalse();

			assertThat(body.get("status")).isInstanceOf(Integer.class);
			assertThat(body.get("status")).isEqualTo(200);
			assertThat(body.get("message")).isEqualTo("testing message");
		}
	}

	@Test
	void checkBuildSuccessMessageWithData() {
		ResponseEntity<Object> response = new APIResponse("testing message").setData("My Object").build();

		assertThat(response.hasBody()).isTrue();
		assertThat(response.getBody()).isNotNull();

		if(response.getBody() instanceof Map)
		{
			Map<String, Object> body = (Map<String, Object>) response.getBody();
			assertThat(body.containsKey("message")).isTrue();
			assertThat(body.containsKey("status")).isTrue();
			assertThat(body.containsKey("data")).isTrue();

			assertThat(body.get("status")).isInstanceOf(Integer.class);
			assertThat(body.get("status")).isEqualTo(200);
			assertThat(body.get("message")).isEqualTo("testing message");
			assertThat(body.get("data")).isEqualTo("My Object");
		}
	}

	@Test
	void checkBuildSuccessMessageWithCollectionData() {
		ResponseEntity<Object> response = new APIResponse("testing message").setData(List.of("My Object 1" , "My Object 2")).build();

		assertThat(response.hasBody()).isTrue();
		assertThat(response.getBody()).isNotNull();

		if(response.getBody() instanceof Map)
		{
			Map<String, Object> body = (Map<String, Object>) response.getBody();
			assertThat(body.containsKey("message")).isTrue();
			assertThat(body.containsKey("status")).isTrue();
			assertThat(body.containsKey("data")).isTrue();
			assertThat(body.containsKey("total")).isTrue();

			assertThat(body.get("status")).isInstanceOf(Integer.class);
			assertThat(body.get("status")).isEqualTo(200);
			assertThat(body.get("message")).isEqualTo("testing message");
			assertThat(body.get("total")).isEqualTo(2);
		}
	}

	@Test
	void checkBuildErrorMessage() {
		ResponseEntity<Object> response = new APIResponse("error message").setStatus(HttpStatus.BAD_REQUEST).build();

		assertThat(response.hasBody()).isTrue();
		assertThat(response.getBody()).isNotNull();

		if(response.getBody() instanceof Map)
		{
			Map<String, Object> body = (Map<String, Object>) response.getBody();
			assertThat(body.containsKey("message")).isTrue();
			assertThat(body.containsKey("status")).isTrue();
			assertThat(body.containsKey("data")).isFalse();

			assertThat(body.get("status")).isInstanceOf(Integer.class);
			assertThat(body.get("status")).isEqualTo(400);
			assertThat(body.get("message")).isEqualTo("error message");
		}
	}
}
