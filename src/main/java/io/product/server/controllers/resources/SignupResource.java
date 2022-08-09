package io.product.server.controllers.resources;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class SignupResource
{
	@NotNull
	private String name;

	@NotNull
	@Email
	private String email;

	@NotNull
	private String password;
}
