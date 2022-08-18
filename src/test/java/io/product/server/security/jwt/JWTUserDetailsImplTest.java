package io.product.server.security.jwt;

import io.product.server.entities.UserEntity;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class JWTUserDetailsImplTest
{

	@Test
	void testValidObject() {
		UUID id = UUID.randomUUID();
		UserEntity john = UserEntity.builder().name("John").email("john@product.io").password("password").build();
		john.setId(id);

		JWTUserDetailsImpl details = JWTUserDetailsImpl.build(john);

		assertThat(details).isNotNull();
		assertThat(details.getAuthorities()).isEmpty();
		assertThat(details.getId()).isEqualTo(id);
		assertThat(details.getName()).isEqualTo("John");
		assertThat(details.getEmail()).isEqualTo("john@product.io");
		assertThat(details.getUsername()).isEqualTo("john@product.io");
		assertThat(details.getPassword()).isEqualTo("password");
		assertThat(details.getLabel()).isEqualTo("{id:"+id+", name:John, email:john@product.io}");
		assertThat(details.isAccountNonExpired()).isTrue();
		assertThat(details.isEnabled()).isTrue();
		assertThat(details.isAccountNonLocked()).isTrue();
		assertThat(details.isCredentialsNonExpired()).isTrue();

	}
}