package io.product.server.services;

import io.product.server.dto.UserDTO;
import io.product.server.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService
{
	@Autowired
	UserRepository repository;

	@Autowired
	ModelMapper modelMapper;

	public List<UserDTO> findAll() {
		log.debug("Find All users");
		return repository.findAll().stream().map(u -> modelMapper.map(u, UserDTO.class)).toList();
	}
}
