package io.product.server.controllers;

import io.product.server.dto.UserDTO;
import io.product.server.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController
{
	@Autowired
	UserRepository repository;

	@Autowired
	ModelMapper modelMapper;

	@GetMapping("/")
	public ResponseEntity<Object> findAll() {
		List<UserDTO> users =  repository.findAll().stream().map(u -> modelMapper.map(u, UserDTO.class)).toList();

		return new APIResponse("Successfully retrieved users")
		        .setData(users)
		        .build();

	}
}
