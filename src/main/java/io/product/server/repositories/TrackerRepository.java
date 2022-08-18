package io.product.server.repositories;

import io.product.server.entities.TrackerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrackerRepository extends JpaRepository<TrackerEntity,UUID>
{
}
