package io.product.server.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

class APIResponseTest
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
			assertThat(body).containsKey("message");
			assertThat(body).containsKey("status");
			assertThat(body).doesNotContainKey("data");

			assertThat(body.get("status")).isInstanceOf(Integer.class);
			assertThat(body).containsEntry("status", 200);
			assertThat(body).containsEntry("message", "testing message");
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
			assertThat(body).containsKey("message");
			assertThat(body).containsKey("status");
			assertThat(body).containsKey("data");

			assertThat(body.get("status")).isInstanceOf(Integer.class);
			assertThat(body).containsEntry("status", 200);
			assertThat(body).containsEntry("message", "testing message");
			assertThat(body).containsEntry("data", "My Object");
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
			assertThat(body).containsKey("message");
			assertThat(body).containsKey("status");
			assertThat(body).containsKey("data");
			assertThat(body).containsKey("total");


			assertThat(body.get("status")).isInstanceOf(Integer.class);
			assertThat(body).containsEntry("status", 200);
			assertThat(body).containsEntry("message", "testing message");
			assertThat(body).containsEntry("total", 2);
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
			assertThat(body).containsKey("message");
			assertThat(body).containsKey("status");
			assertThat(body).doesNotContainKey("data");

			assertThat(body.get("status")).isInstanceOf(Integer.class);
			assertThat(body).containsEntry("status", 400);
			assertThat(body).containsEntry("message", "error message");
		}
	}
}
