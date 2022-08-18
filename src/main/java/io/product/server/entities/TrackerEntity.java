package io.product.server.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name="trackers")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class TrackerEntity extends BaseEntity
{
	@Column(name = "name", length = 255, nullable = false)
	private String name;

	@Column(name = "description", length = 512, nullable = false)
	private String description;

	@Column(name="default",columnDefinition = "boolean default true")
	private boolean defaultTracker;


	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "tracker_creators",
	             joinColumns = @JoinColumn(name = "tracker_id"),
	             inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> creators = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "tracker_notifications",
	             joinColumns = @JoinColumn(name = "tracker_id"),
	             inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> notifications = new HashSet<>();
}
