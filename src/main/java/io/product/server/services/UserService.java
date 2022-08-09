package io.product.server.services;

import io.product.server.dto.User;
import io.product.server.exceptions.UserExistException;

import java.util.List;

public interface UserService
{
	List<User> findAll();

	User createUser(String email, String password, String name) throws UserExistException;
}
