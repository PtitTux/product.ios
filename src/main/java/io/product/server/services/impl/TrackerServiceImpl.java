package io.product.server.services.impl;

import io.product.server.dto.Tracker;
import io.product.server.entities.TrackerEntity;
import io.product.server.exceptions.TrackerExistException;
import io.product.server.repositories.TrackerRepository;
import io.product.server.services.TrackerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

	@Override
	public Tracker createTracker(String name, String description, boolean isDefault) throws TrackerExistException
	{
		log.debug("Create Tracker");

		Optional<TrackerEntity> existTracker = repository.findByName(name);
		if (existTracker.isPresent())
		{
			throw new TrackerExistException();
		}

		TrackerEntity entity = TrackerEntity.builder().name(name).description(description).defaultTracker(isDefault).build();

		entity = repository.save(entity);

		log.debug("Tracker created with id " + entity.getId());

		// set all others trackers to default false
		if(isDefault) {
			repository.updateTrackersToNotDefaultExceptThis(entity.getId());
		}

		return this.modelMapper.map(entity, Tracker.class);
	}
}
