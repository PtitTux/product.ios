package io.product.server.exceptions;

public class UserNotExistException extends AppException
{

	public UserNotExistException() {
		super("User not exist with this email");
	}
}
