package io.product.server.repositories;

import io.product.server.entities.TrackerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface TrackerRepository extends JpaRepository<TrackerEntity,UUID>
{
	Optional<TrackerEntity> findByName(String name);

	@Query("UPDATE TrackerEntity SET defaultTracker = false where id != :id")
	@Modifying
	@Transactional
	void updateTrackersToNotDefaultExceptThis(UUID id);
}
