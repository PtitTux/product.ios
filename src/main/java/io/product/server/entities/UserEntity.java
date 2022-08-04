package io.product.server.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Data
@Builder
@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity
{
	@Column(name = "email", length = 255, nullable = false, unique = true)
	private String email;

	@Column(name = "password", length = 255, nullable = false)
	private String password;

	@Column(name = "name", length = 512, nullable = false)
	private String name;

	@Column(name="last_connection",columnDefinition = "TIMESTAMP")
	private LocalDateTime lastConnection;

	@Column(name="status",columnDefinition = "boolean default true")
	private boolean status;

}
