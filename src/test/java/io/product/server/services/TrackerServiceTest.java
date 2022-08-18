package io.product.server.services;

import io.product.server.dto.Tracker;
import io.product.server.dto.User;
import io.product.server.entities.TrackerEntity;
import io.product.server.entities.UserEntity;
import io.product.server.exceptions.TrackerExistException;
import io.product.server.exceptions.UserExistException;
import io.product.server.exceptions.UserNotExistException;
import io.product.server.exceptions.UserPasswordNotMatchException;
import io.product.server.repositories.TrackerRepository;
import io.product.server.repositories.UserRepository;
import io.product.server.security.jwt.JWTUtils;
import io.product.server.services.impl.TrackerServiceImpl;
import io.product.server.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrackerServiceTest
{
	@InjectMocks
	TrackerServiceImpl service;

	@Mock
	TrackerRepository dao;

	@Mock
	ModelMapper modelMapper;

	TrackerEntity defaultTracker;
	TrackerEntity anothertracker;

	@BeforeEach
	void setUp()
	{
		service = new TrackerServiceImpl(dao, new ModelMapper());

		defaultTracker = TrackerEntity.builder().name("defaultTracker").description("description").defaultTracker(true).build();
		anothertracker = TrackerEntity.builder().name("anotherTracker").description("description").defaultTracker(false).build();
		anothertracker.setId(UUID.randomUUID());
	}

	@Test
	void findAll()
	{
		when(dao.findAll()).thenReturn(List.of(defaultTracker,anothertracker));

		//test
		List<Tracker> list = service.findAll();

		assertThat(list).hasSize(2);
		verify(dao, times(1)).findAll();
	}

	@Test
	void createTrackerWithoutDefault() {
		when(dao.findByName(any())).thenReturn(Optional.empty());
		when(dao.save(any())).thenReturn(anothertracker);

		try
		{
			Tracker t = service.createTracker("anotherTracker", null, false);

			assertThat(t.getName()).isEqualTo("anotherTracker");
			assertThat(t.isDefaultTracker()).isFalse();
		}
		catch (TrackerExistException e)
		{
			throw new RuntimeException(e);
		}

	}

	@Test
	void createTrackerWithDefault() {
		when(dao.findByName(any())).thenReturn(Optional.empty());
		when(dao.save(any())).thenReturn(defaultTracker);

		try
		{
			Tracker t = service.createTracker("defaultTracker", null, true);

			assertThat(t.getName()).isEqualTo("defaultTracker");
			assertThat(t.isDefaultTracker()).isTrue();
		}
		catch (TrackerExistException e)
		{
			throw new RuntimeException(e);
		}

	}

	@Test
	void createTrackerWithNameExist() {
		when(dao.findByName(any())).thenReturn(Optional.of(anothertracker));

		assertThatThrownBy(() -> service.createTracker("testing", null, false)).isInstanceOf(TrackerExistException.class);
	}
}