package io.product.server.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO
{
	private UUID id;

	@NotNull
	private String name;

	@NotNull
	@Email
	private String email;
}
