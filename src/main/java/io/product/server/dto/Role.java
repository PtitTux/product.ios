package io.product.server.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper=true)
public class Role extends BaseBean
{
	@NotNull
	private String name;
}
