package io.product.server.repositories;

import io.product.server.dto.UserDTO;
import io.product.server.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@DataJpaTest
class UserRepositoryTest
{
	@Autowired
	UserRepository userRepository;

	private LocalDateTime now;

	@BeforeEach
	void setUp() {
		now = LocalDateTime.now();
	}


	@Test
	void createUserCheck() {
		UserEntity user = new UserEntity();
		user.setName("name");
		user.setEmail("email");
		user.setStatus(true);
		user.setPassword("password");
		user.setLastConnection(now);

		user = userRepository.save(user);

		assertThat(user.getId()).isNotNull();
		assertThat(user.getName()).isEqualTo("name");
		assertThat(user.getEmail()).isEqualTo("email");
		assertThat(user.getPassword()).isEqualTo("password");
		assertThat(user.getLastConnection()).isEqualTo(now);
		assertThat(user.isStatus()).isTrue();
	}

	@Test
	void createUserWithConstructor() {
		UserEntity user = new UserEntity("email","password","name", now,true);

		user = userRepository.save(user);

		assertThat(user.getId()).isNotNull();
		assertThat(user.getName()).isEqualTo("name");
		assertThat(user.getEmail()).isEqualTo("email");
		assertThat(user.getPassword()).isEqualTo("password");
		assertThat(user.getLastConnection()).isEqualTo(now);
		assertThat(user.isStatus()).isTrue();
	}


	@Test
	void compareTwoSameUsers() {
		UserEntity user1 = new UserEntity("email","password","name", now,true);
		UserEntity user2 = new UserEntity("email","password","name", now,true);

		user1 = userRepository.save(user1);
		user2 = userRepository.save(user2);

		assertThat(user1.hashCode()).isNotEqualTo(user2.hashCode());
		assertThat(user1.toString()).isEqualTo(user2.toString());
		assertThat(user1).isNotEqualTo(user2);
	}
}
