package io.product.server.controllers;

import io.product.server.controllers.resources.TrackerListResource;
import io.product.server.services.TrackerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
