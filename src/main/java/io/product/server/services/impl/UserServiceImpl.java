package io.product.server.services.impl;

import io.product.server.dto.User;
import io.product.server.entities.UserEntity;
import io.product.server.repositories.UserRepository;
import io.product.server.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService
{
	private final UserRepository repository;

	private final ModelMapper modelMapper;

	public UserServiceImpl(UserRepository repository, ModelMapper modelMapper)
	{
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<User> findAll() {
		log.debug("Find All users");
		return repository.findAll().stream().map(u -> modelMapper.map(u, User.class)).toList();
	}

	@Override
	public User createUser(String email, String password, String name) {

		Optional<UserEntity> userExist = repository.findByEmail(email);

		if(userExist.isPresent()) {

		}

		return null;
	}
}
