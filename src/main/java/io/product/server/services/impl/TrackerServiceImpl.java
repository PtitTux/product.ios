package io.product.server.services.impl;

import io.product.server.dto.Tracker;
import io.product.server.dto.User;
import io.product.server.repositories.TrackerRepository;
import io.product.server.repositories.UserRepository;
import io.product.server.security.jwt.JWTUtils;
import io.product.server.services.TrackerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TrackerServiceImpl implements TrackerService
{
	private final TrackerRepository repository;

	private final ModelMapper modelMapper;

	public TrackerServiceImpl(TrackerRepository repository, ModelMapper modelMapper)
	{
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<Tracker> findAll()
	{
		log.debug("Find All trackers");
		return repository.findAll().stream().map(u -> modelMapper.map(u, Tracker.class)).toList();
	}
}
