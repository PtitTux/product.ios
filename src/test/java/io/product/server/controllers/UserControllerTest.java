package io.product.server.controllers;

import io.product.server.controllers.resources.UserListResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserControllerTest
{
	@Autowired
	UserController userController;


	@Test
	void testFindAll() {
		ResponseEntity<List<UserListResource>> response = userController.findAll();

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.hasBody()).isTrue();
	}
}
