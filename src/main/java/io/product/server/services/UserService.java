package io.product.server.services;

import io.product.server.dto.User;
import io.product.server.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService
{
	private final UserRepository repository;

	private final ModelMapper modelMapper;

	public UserService(UserRepository repository, ModelMapper modelMapper)
	{
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	public List<User> findAll() {
		log.debug("Find All users");
		return repository.findAll().stream().map(u -> modelMapper.map(u, User.class)).toList();
	}
}
