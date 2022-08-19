package io.product.server;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public class BaseTestWithContainers
{
	@Container
	public static PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>("postgres:bullseye").withDatabaseName("product.io");

	@DynamicPropertySource
	static void postgresqlProperties(DynamicPropertyRegistry registry)
	{
		registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgresqlContainer::getUsername);
		registry.add("spring.datasource.password", postgresqlContainer::getPassword);
	}
}
