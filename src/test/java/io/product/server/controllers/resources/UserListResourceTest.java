package io.product.server.controllers.resources;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserListResourceTest
{

	@Test
	void buildUserList() {
		UUID id = UUID.randomUUID();

		UserListResource user = new UserListResource();
		user.setId(id);
		user.setName("name");
		user.setEmail("test@product.io");

		assertThat(user.getId()).isEqualTo(id);
		assertThat(user.getName()).isEqualTo("name");
		assertThat(user.getEmail()).isEqualTo("test@product.io");
	}
}