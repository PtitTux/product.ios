package io.product.server.security.jwt;

import io.product.server.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class JWTFilterTest
{
	@Autowired
	private TestRestTemplate testClient;

	private JWTUtils jwtUtils;

	@BeforeEach
	void setUp() {
		jwtUtils = new JWTUtils();
		ReflectionTestUtils.setField(jwtUtils, "jwtSecret", "MyJwTSecret2022");
		ReflectionTestUtils.setField(jwtUtils, "jwtExpirationMs", 86400000);
	}

	@Test
	void testPublicAPI() {
		Map<String,String> signup=new HashMap<>();
		signup.put("email","jane@product.io");
		signup.put("name","Jane");
		signup.put("password","P@ssw@rd");

		ResponseEntity<String> response = testClient.postForEntity("/auth/signup",signup, String.class);
		assertThat(response.getStatusCode().value()).isEqualTo(201);
	}

	@Test
	void testPrivateAPIWithoutAuth() {
		ResponseEntity<String> response = testClient.getForEntity("/users", String.class);
		assertThat(response.getStatusCode().value()).isEqualTo(403);
	}

	@Test
	void testPrivateAPIWithAuth() {
		UserEntity john = UserEntity.builder().name("user-test").email("test@product.io").build();
		String token = jwtUtils.generateToken(john);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);

		RequestEntity requestEntity = new RequestEntity(headers, HttpMethod.GET, URI.create("/users"));

		ResponseEntity<String> response = testClient.exchange(requestEntity, String.class);
		assertThat(response.getStatusCode().value()).isEqualTo(200);
	}

	@Test
	void testPrivateAPIWithAuthBearer() {
		UserEntity john = UserEntity.builder().name("user-test").email("test@product.io").build();
		String token = jwtUtils.generateToken(john);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+token);

		RequestEntity requestEntity = new RequestEntity(headers, HttpMethod.GET, URI.create("/users"));

		ResponseEntity<String> response = testClient.exchange(requestEntity, String.class);
		assertThat(response.getStatusCode().value()).isEqualTo(200);
	}

	@Test
	void testPrivateAPIWithAuthBearerLowercase() {
		UserEntity john = UserEntity.builder().name("user-test").email("test@product.io").build();
		String token = jwtUtils.generateToken(john);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "bearer "+token);

		RequestEntity requestEntity = new RequestEntity(headers, HttpMethod.GET, URI.create("/users"));

		ResponseEntity<String> response = testClient.exchange(requestEntity, String.class);
		assertThat(response.getStatusCode().value()).isEqualTo(200);
	}

	@Test
	void testPrivateAPIWithAuthEmpty() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "");

		RequestEntity requestEntity = new RequestEntity(headers, HttpMethod.GET, URI.create("/users"));

		ResponseEntity<String> response = testClient.exchange(requestEntity, String.class);
		assertThat(response.getStatusCode().value()).isEqualTo(403);
	}

	@Test
	void testPrivateAPIWithAuthPassed() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJJc3N1ZXIiOiJwcm9kdWN0LmlvIiwiZXhwIjoxNjA5NTA5NjAwLCJpYXQiOjE2MDk1MDI0MDAsIlN1YmplY3QiOiJ0ZXN0QHByb2R1Y3QuaW8ifQ.FnafR08_z78-pd6G3WKmr40V4dtcsgoE0mhS0_Q7EnsvDn6B35DD24AZ8JD0XNGL8JCKSjl2peaXRIgkcVUIvw");

		RequestEntity requestEntity = new RequestEntity(headers, HttpMethod.GET, URI.create("/users"));

		ResponseEntity<String> response = testClient.exchange(requestEntity, String.class);
		assertThat(response.getStatusCode().value()).isEqualTo(403);
	}
}