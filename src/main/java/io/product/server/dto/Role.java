package io.product.server.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper=true)
public class Role extends BaseBean
{
	@NotNull
	private String name;
}
