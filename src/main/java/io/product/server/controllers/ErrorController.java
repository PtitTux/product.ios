package io.product.server.controllers;

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
}
