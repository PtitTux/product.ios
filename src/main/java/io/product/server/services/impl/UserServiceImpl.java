package io.product.server.services.impl;

import io.product.server.dto.User;
import io.product.server.entities.UserEntity;
import io.product.server.exceptions.UserExistException;
import io.product.server.exceptions.UserNotExistException;
import io.product.server.exceptions.UserPasswordNotMatchException;
import io.product.server.repositories.UserRepository;
import io.product.server.security.JWTUtils;
import io.product.server.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService
{
	private final UserRepository repository;

	private final ModelMapper modelMapper;

	private final PasswordEncoder passwordEncoder;

	private final JWTUtils jwt;
	public UserServiceImpl(UserRepository repository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, JWTUtils jwt)
	{
		this.repository = repository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
		this.jwt = jwt;
	}

	@Override
	public List<User> findAll()
	{
		log.debug("Find All users");
		return repository.findAll().stream().map(u -> modelMapper.map(u, User.class)).toList();
	}

	@Override
	public User createUser(String email, String password, String name) throws UserExistException
	{

		Optional<UserEntity> userExist = repository.findByEmail(email);

		if (userExist.isPresent())
		{
			throw new UserExistException();
		}

		UserEntity createdUser = UserEntity.builder().name(name).email(email).password(this.passwordEncoder.encode(password)).status(true).build();

		createdUser = this.repository.save(createdUser);

		return this.modelMapper.map(createdUser, User.class);
	}

	@Override
	public User loginUser(String email, String password) throws UserNotExistException, UserPasswordNotMatchException
	{
		Optional<UserEntity> userExistOpt = repository.findByEmail(email);

		if (userExistOpt.isEmpty())
		{
			throw new UserNotExistException();
		}

		UserEntity userExist = userExistOpt.get();

		if (!passwordEncoder.matches(password, userExist.getPassword()))
		{
			throw new UserPasswordNotMatchException();
		}

		// Update lastConnection date
		userExist.setLastConnection(LocalDateTime.now());
		userExist = repository.save(userExist);

		User user = modelMapper.map(userExist, User.class);
		user.setAccessToken(jwt.generateToken(userExist));

		return user;
	}
}
