package io.product.server.repositories;

import io.product.server.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
		UserEntity user = new UserEntity("email2","password","name2", now,true);

		user = userRepository.save(user);

		assertThat(user.getId()).isNotNull();
		assertThat(user.getName()).isEqualTo("name2");
		assertThat(user.getEmail()).isEqualTo("email2");
		assertThat(user.getPassword()).isEqualTo("password");
		assertThat(user.getLastConnection()).isEqualTo(now);
		assertThat(user.isStatus()).isTrue();
	}
}
