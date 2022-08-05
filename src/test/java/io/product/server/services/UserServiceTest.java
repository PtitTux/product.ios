package io.product.server.services;

import io.product.server.dto.UserDTO;
import io.product.server.entities.UserEntity;
import io.product.server.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest
{
	@InjectMocks
	UserService service;

	@Mock
	UserRepository dao;

	@Mock
	ModelMapper modelMapper;

	@Test
	void findAll()
	{
		UserEntity john = new UserEntity(UUID.randomUUID(),"John", "john@product.io");
		UserEntity alex = new UserEntity(UUID.randomUUID(),"Alex", "alex@product.io");
		UserEntity steve = new UserEntity(UUID.randomUUID(),"Steve", "steve@product.io");


		when(dao.findAll()).thenReturn(List.of(john, alex, steve));

		//test
		List<UserDTO> usersList = service.findAll();

		assertEquals(3, usersList.size());
		verify(dao, times(1)).findAll();
	}
}