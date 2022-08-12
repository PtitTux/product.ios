package io.product.server.services;

import io.product.server.dto.User;
import io.product.server.entities.UserEntity;
import io.product.server.exceptions.UserExistException;
import io.product.server.exceptions.UserNotExistException;
import io.product.server.exceptions.UserPasswordNotMatchException;
import io.product.server.repositories.UserRepository;
import io.product.server.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest
{
	@InjectMocks
	UserServiceImpl service;

	@Mock
	UserRepository dao;

	@Mock
	ModelMapper modelMapper;

	@Mock
	PasswordEncoder passwordEncoder;

	UserEntity john;

	@BeforeEach
	void setUp()
	{
		service = new UserServiceImpl(dao, new ModelMapper(), new BCryptPasswordEncoder(10));

		john = UserEntity.builder().name("John").email("john@product.io").password(new BCryptPasswordEncoder(10).encode("password")).status(true).build();
	}

	@Test
	void findAll()
	{
		UserEntity john = new UserEntity(UUID.randomUUID(), "John", "john@product.io");
		UserEntity alex = new UserEntity(UUID.randomUUID(), "Alex", "alex@product.io");
		UserEntity steve = new UserEntity(UUID.randomUUID(), "Steve", "steve@product.io");

		when(dao.findAll()).thenReturn(List.of(john, alex, steve));

		//test
		List<User> usersList = service.findAll();

		assertThat(usersList).hasSize(3);
		verify(dao, times(1)).findAll();
	}

	@Test
	void createUser()
	{
		try
		{
			when(dao.findByEmail("john@product.io")).thenReturn(Optional.empty());
			when(dao.save(any())).thenReturn(john);

			User created = service.createUser("john@product.io", "password", "John");

			assertThat(created.getEmail()).isEqualTo("john@product.io");
			assertThat(created.getName()).isEqualTo("John");
			assertThat(created.isStatus()).isTrue();
		}
		catch (UserExistException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Test
	void createUserPresent()
	{

		when(dao.findByEmail("john@product.io")).thenReturn(Optional.of(john));

		assertThatThrownBy(() -> service.createUser("john@product.io", "password", "John")).isInstanceOf(UserExistException.class);

	}

	@Test
	void loginUser()
	{
		when(dao.findByEmail("john@product.io")).thenReturn(Optional.of(john));
		when(dao.save(any())).thenReturn(john);

		try
		{
			User existUser = service.loginUser("john@product.io", "password");

			assertThat(existUser.getEmail()).isEqualTo("john@product.io");
		}
		catch (UserNotExistException | UserPasswordNotMatchException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Test
	void loginUserNotExist()
	{
		when(dao.findByEmail(any())).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.loginUser("jane@product.io", "password")).isInstanceOf(UserNotExistException.class);
	}

	@Test
	void loginUserWrongPassword()
	{
		when(dao.findByEmail(any())).thenReturn(Optional.of(john));

		assertThatThrownBy(() -> service.loginUser("john@product.io", "password2")).isInstanceOf(UserPasswordNotMatchException.class);
	}
}