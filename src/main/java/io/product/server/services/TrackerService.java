package io.product.server.services;

import io.product.server.dto.Tracker;
import io.product.server.exceptions.TrackerExistException;
import io.product.server.exceptions.TrackerNotExistException;

import java.util.List;
import java.util.UUID;

public interface TrackerService
{
	List<Tracker> findAll();

	Tracker createTracker(String name, String description, boolean isDefault) throws TrackerExistException;

	Tracker getTrackerById(UUID id) throws TrackerNotExistException;
	Tracker updateTracker(Tracker t) throws TrackerNotExistException;
}
