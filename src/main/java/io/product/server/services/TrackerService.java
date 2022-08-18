package io.product.server.services;

import io.product.server.dto.Tracker;

import java.util.List;

public interface TrackerService
{
	List<Tracker> findAll();
}
