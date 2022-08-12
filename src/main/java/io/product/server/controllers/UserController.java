package io.product.server.controllers;

import io.product.server.controllers.resources.UserListResource;
import io.product.server.security.jwt.JWTUserDetailsImpl;
import io.product.server.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController
{
	private final UserService service;
	private final ModelMapper modelMapper;

	public UserController(UserService service, ModelMapper modelMapper)
	{
		this.service = service;
		this.modelMapper = modelMapper;
	}

	@GetMapping("")
	public ResponseEntity<Object> findAll(Authentication authentication)
	{
		return new APIResponse<>("Successfully retrieved users").setData(service.findAll()
		                                                                        .stream()
		                                                                        .map(u -> this.modelMapper.map(u, UserListResource.class))
		                                                                        .toList()).build();
	}

	private String getLabel(Authentication auth)
	{
		return Optional.of(auth)
		               .map(Authentication::getPrincipal)
		               .filter(JWTUserDetailsImpl.class::isInstance)
		               .map(JWTUserDetailsImpl.class::cast)
		               .map(JWTUserDetailsImpl::getLabel)
		               .orElseGet(auth::getName);
	}
}
