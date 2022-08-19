package io.product.server.security.jwt;

import io.product.server.entities.UserEntity;
import io.product.server.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JWTUserDetailsServiceImplTest
{

	@Autowired
	UserRepository userRepository;

	JWTUserDetailsServiceImpl service;

	@BeforeEach
	void setUp()
	{
		this.service = new JWTUserDetailsServiceImpl(userRepository);
	}

	@Test
	void loadUserWithGoodUsername()
	{
		UserEntity john = UserEntity.builder().name("John").email("john@product.io").password("password").build();

		this.userRepository.save(john);

		UserDetails user = this.service.loadUserByUsername("john@product.io");

		assertThat(user).isNotNull();
	}

	@Test
	void loadUserWithWrongUsername()
	{
		assertThatThrownBy(() -> this.service.loadUserByUsername("MyEmailIsNotExist@product.io")).isInstanceOf(UsernameNotFoundException.class);
	}
}