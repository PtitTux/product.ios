package io.product.server.controllers.resources;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResource
{
	private UUID id;
	private String name;
	private String email;
}
