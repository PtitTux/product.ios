package io.product.server.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@Entity
@Table(name="roles")
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity extends BaseEntity
{
	@Column(name = "name", length = 255, nullable = false, unique = true)
	private String name;

	@Column(name = "label", length = 512, nullable = false)
	private String label;
}
