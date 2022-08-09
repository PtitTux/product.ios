package io.product.server.controllers;

import io.product.server.controllers.resources.SignupResource;
import io.product.server.controllers.resources.UserResource;
import io.product.server.dto.User;
import io.product.server.exceptions.UserExistException;
import io.product.server.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController
{

	private final UserService service;
	private final ModelMapper modelMapper;

	public AuthController(UserService service, ModelMapper modelMapper)
	{
		this.service = service;
		this.modelMapper = modelMapper;
	}

	@PostMapping("/signup")
	public ResponseEntity<Object> signup(@Valid @RequestBody SignupResource signup) throws UserExistException
	{
		User user = this.service.createUser(signup.getEmail(), signup.getPassword(), signup.getName());

		return new APIResponse<>("user signup with success").setStatus(HttpStatus.CREATED)
		                                                                 .setData(this.modelMapper.map(user, UserResource.class))
		                                                                 .build();
	}
}
