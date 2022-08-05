package io.product.server.repositories;

import io.product.server.entities.RoleEntity;
import io.product.server.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
class RoleRepositoryTest
{

	@Autowired
	RoleRepository repository;

	@Test
	void createRoleCheck() {
		RoleEntity role = new RoleEntity();
		role.setName("name");
		role.setLabel("label");

		role = repository.save(role);

		assertThat(role.getId()).isNotNull();
		assertThat(role.getName()).isEqualTo("name");
		assertThat(role.getLabel()).isEqualTo("label");
	}

	@Test
	void createRoleWithContructor() {
		RoleEntity role = new RoleEntity("name","label");

		role = repository.save(role);

		assertThat(role.getId()).isNotNull();
		assertThat(role.getName()).isEqualTo("name");
		assertThat(role.getLabel()).isEqualTo("label");
	}
}