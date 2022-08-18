package io.product.server.controllers;

import io.product.server.controllers.resources.CreateTrackerResource;
import io.product.server.controllers.resources.TrackerListResource;
import io.product.server.controllers.resources.TrackerResource;
import io.product.server.dto.Tracker;
import io.product.server.exceptions.TrackerExistException;
import io.product.server.services.TrackerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

	@PostMapping("")
	public ResponseEntity<Object> createTracker(@Valid @RequestBody CreateTrackerResource t) throws TrackerExistException
	{
		Tracker tracker = this.service.createTracker(t.getName(), t.getDescription(), t.isDefaultTracker());

		return new APIResponse<>("tracker signup with success").setStatus(HttpStatus.CREATED).setData(this.modelMapper.map(tracker, TrackerResource.class)).build();
	}
}
