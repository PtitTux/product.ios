package io.product.server.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.MappedSuperclass;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Data
@MappedSuperclass
@Slf4j
public abstract class BaseBean
{
	private UUID id;
	protected LocalDateTime createdAt;
	protected LocalDateTime updatedAt;

	public void setProperty(String name, Object value)
	{
		try
		{
			Optional<PropertyDescriptor> propertyDescriptor = Arrays.stream(Introspector.getBeanInfo(getClass()).getPropertyDescriptors())
			                                                        .filter(p -> p.getName().equals(name))
			                                                        .findFirst();
			if (propertyDescriptor.isPresent())
			{
				Method method = propertyDescriptor.get().getWriteMethod();
				if (method != null)
				{
					method.invoke(this, value);
				}
			}
		}
		catch (IntrospectionException | IllegalAccessException | InvocationTargetException e)
		{
			log.debug("Impossible to update field: ", e);
		}


	}
}
