package io.product.server.services;

import io.product.server.dto.User;

import java.util.List;

public interface UserService
{
	List<User> findAll();

	User createUser(String email, String password, String name);
}
