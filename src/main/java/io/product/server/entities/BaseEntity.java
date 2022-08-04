package io.product.server.entities;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class BaseEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@CreatedDate
	private Date createdAt;

	@LastModifiedDate
	private Date updatedAt;
}
