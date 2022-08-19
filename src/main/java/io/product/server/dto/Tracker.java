package io.product.server.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper=true)
public class Tracker extends BaseBean
{


	@NotNull
	private String name;

	private String description;

	private String color;

	private boolean defaultTracker;
}
