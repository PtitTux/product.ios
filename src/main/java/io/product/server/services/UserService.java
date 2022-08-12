package io.product.server.services;

import io.product.server.dto.User;
import io.product.server.exceptions.UserExistException;
import io.product.server.exceptions.UserNotExistException;
import io.product.server.exceptions.UserPasswordNotMatchException;

import java.util.List;

public interface UserService
{
	List<User> findAll();

	User createUser(String email, String password, String name) throws UserExistException;

	User loginUser(String email, String password) throws UserNotExistException, UserPasswordNotMatchException;
}
