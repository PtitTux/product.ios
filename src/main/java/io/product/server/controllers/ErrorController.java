package io.product.server.controllers;

import io.product.server.exceptions.AppException;
import io.product.server.exceptions.UserExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController
{
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Object> exception(MethodArgumentNotValidException exception)
	{
		return new APIResponse<>("Invalid parameter").setStatus(HttpStatus.BAD_REQUEST).build();
	}

	@ExceptionHandler(value = AppException.class)
	public ResponseEntity<Object> exception(AppException exception)
	{
		return new APIResponse<>(exception.getMessage()).setStatus(HttpStatus.BAD_REQUEST).build();
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> exception(Exception exception)
	{
		return new APIResponse<>("general error").setStatus(HttpStatus.BAD_REQUEST).build();
	}
}
