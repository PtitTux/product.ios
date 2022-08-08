package io.product.server.controllers.resources;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
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
