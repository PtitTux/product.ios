package io.product.server.repositories;

import io.product.server.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@DataJpaTest
class UserRepositoryTest
{
	@Autowired
	UserRepository userRepository;


	@Test
	void createUserCheckIdNotNull() {
		UserEntity user = new UserEntity();
		user.setName("name");
		user.setEmail("email");

		user = userRepository.save(user);

		assertThat(user.getId()).isNotNull();
		assertThat(user.getName()).isEqualTo("name");
		assertThat(user.getEmail()).isEqualTo("email");
	}
}
