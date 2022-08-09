package io.product.server.exceptions;

public class UserExistException extends AppException
{

	public UserExistException() {
		super("User already exist with this email");
	}
}
