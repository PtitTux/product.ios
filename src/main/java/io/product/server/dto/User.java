package io.product.server.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper=true)
public class User extends BaseBean
{
	@NotNull
	private String name;

	@NotNull
	@Email
	private String email;

	private boolean status;

	private LocalDateTime lastConnection;

	private String accessToken;
}
