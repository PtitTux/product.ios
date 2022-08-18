package io.product.server.services;

import io.product.server.dto.Tracker;
import io.product.server.exceptions.TrackerExistException;

import java.util.List;

public interface TrackerService
{
	List<Tracker> findAll();

	Tracker createTracker(String name, String description, boolean isDefault) throws TrackerExistException;
}
