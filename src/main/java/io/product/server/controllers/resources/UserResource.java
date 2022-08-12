package io.product.server.controllers.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResource
{
	private UUID id;
	private String name;
	private String email;
	private String accessToken;
}
