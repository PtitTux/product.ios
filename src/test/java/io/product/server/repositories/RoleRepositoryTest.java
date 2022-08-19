package io.product.server.repositories;

import io.product.server.entities.RoleEntity;
import io.product.server.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
		RoleEntity role = new RoleEntity("name2","label");

		role = repository.save(role);

		assertThat(role.getId()).isNotNull();
		assertThat(role.getName()).isEqualTo("name2");
		assertThat(role.getLabel()).isEqualTo("label");
	}
}