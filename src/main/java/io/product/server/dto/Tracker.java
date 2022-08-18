package io.product.server.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class Tracker
{
	private UUID id;

	@NotNull
	private String name;

	private String description;

	private boolean defaultTracker;
}
