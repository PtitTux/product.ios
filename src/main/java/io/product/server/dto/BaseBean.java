package io.product.server.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.MappedSuperclass;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
@Slf4j
public abstract class BaseBean
{
	private UUID id;
	protected LocalDateTime createdAt;
	protected LocalDateTime updatedAt;

	public void setProperty(String name, Object value) {
		try
		{
			Field field = getClass().getDeclaredField(name);
			field.setAccessible(true);
			field.set(this, value);
		}
		catch (NoSuchFieldException | IllegalAccessException e)
		{
			log.debug("Impossible to update field: ",e);
		}

	}
}
