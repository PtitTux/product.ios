package io.product.server.controllers;

import io.product.server.exceptions.UserExistException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.MethodParameter;
import org.springframework.data.rest.core.ValidationErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ErrorControllerTest
{

	@Autowired
	ErrorController errorController;

	@Test
	void exceptionMethodArgumentNotValidException() throws NoSuchMethodException
	{
		MethodParameter parameter = new MethodParameter(APIResponse.class.getMethod("getMessage"), -1);
		BindingResult result = new BeanPropertyBindingResult(this,"errorController");

		ResponseEntity<Object> response = errorController.exception(new MethodArgumentNotValidException(parameter,result));

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(response.hasBody()).isTrue();
	}

	@Test
	void exceptionAppException()
	{
		ResponseEntity<Object> response = errorController.exception(new UserExistException());

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(response.hasBody()).isTrue();
	}

	@Test
	void exceptionOther()
	{
		ResponseEntity<Object> response = errorController.exception(new Exception());

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(response.hasBody()).isTrue();
	}
}