package io.product.server.controllers.resources;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class CreateTrackerResource
{
	@NotNull
	private String name;
	private String description;

	private boolean defaultTracker;
}
