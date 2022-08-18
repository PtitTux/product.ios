package io.product.server.controllers.resources;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateTrackerResource
{
	@NotNull
	private String name;
	private String description;

	private boolean defaultTracker;
}
