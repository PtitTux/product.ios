package io.product.server.exceptions;

public class TrackerExistException extends AppException
{

	public TrackerExistException() {
		super("Tracker already exist with this name");
	}
}
