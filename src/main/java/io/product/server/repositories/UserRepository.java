package io.product.server.repositories;

import io.product.server.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity,UUID>
{
	Optional<UserEntity> findByEmail(String email);
}
