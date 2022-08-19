package io.product.server.controllers.resources;

import lombok.Data;

import java.util.UUID;

@Data
public class TrackerListResource
{
	private UUID id;
	private String name;
	private boolean defaultTracker;
}
