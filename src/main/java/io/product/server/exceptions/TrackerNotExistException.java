package io.product.server.exceptions;

public class TrackerNotExistException extends AppException
{

	public TrackerNotExistException() {
		super("Tracker not exist");
	}
}
