package io.product.server.controllers;

import io.product.server.controllers.resources.CreateTrackerResource;
import io.product.server.controllers.resources.TrackerListResource;
import io.product.server.controllers.resources.TrackerResource;
import io.product.server.dto.Tracker;
import io.product.server.exceptions.TrackerExistException;
import io.product.server.exceptions.TrackerNotExistException;
import io.product.server.services.TrackerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/trackers")
@Slf4j
public class TrackerController
{
	private final TrackerService service;
	private final ModelMapper modelMapper;

	public TrackerController(TrackerService service, ModelMapper modelMapper)
	{
		this.service = service;
		this.modelMapper = modelMapper;
	}

	@GetMapping("")
	public ResponseEntity<Object> findAll()
	{
		return new APIResponse<>("Successfully retrieved trackers").setData(service.findAll()
		                                                                           .stream()
		                                                                           .map(u -> this.modelMapper.map(u, TrackerListResource.class))
		                                                                           .toList()).build();
	}

	@PutMapping("")
	public ResponseEntity<Object> createTracker(@Valid @RequestBody CreateTrackerResource t) throws TrackerExistException
	{
		Tracker tracker = this.service.createTracker(t.getName(), t.getDescription(), t.isDefaultTracker());

		return new APIResponse<>("tracker created with success").setStatus(HttpStatus.CREATED).setData(this.modelMapper.map(tracker, TrackerResource.class)).build();
	}

	@PostMapping("{id}")
	public ResponseEntity<Object> updateTracker(@PathVariable("id") String id, @Valid @RequestBody Map<String, Object> body) throws TrackerNotExistException
	{
		Tracker tracker = this.service.getTrackerById(UUID.fromString(id));

		// Update tracker attributes
		body.forEach(tracker::setProperty);

		this.service.updateTracker(tracker);

		return new APIResponse<>("tracker updated with success").setStatus(HttpStatus.OK).setData(this.modelMapper.map(tracker, TrackerResource.class)).build();
	}

}
