package io.product.server.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@Entity
@Table(name="roles")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class RoleEntity extends BaseEntity
{
	@Column(name = "name", length = 255, nullable = false, unique = true)
	private String name;

	@Column(name = "label", length = 512, nullable = false)
	private String label;
}
