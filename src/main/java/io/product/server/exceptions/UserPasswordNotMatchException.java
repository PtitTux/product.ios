package io.product.server.exceptions;

public class UserPasswordNotMatchException extends AppException
{

	public UserPasswordNotMatchException() {
		super("Password is wrong");
	}
}
