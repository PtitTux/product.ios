package io.product.server.controllers;

import io.product.server.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController
{
	private final UserService service;

	public UserController(UserService service)
	{
		this.service = service;
	}

	@GetMapping("")
	public ResponseEntity<Object> findAll()
	{
		return new APIResponse("Successfully retrieved users").setData(service.findAll()).build();
	}
}
