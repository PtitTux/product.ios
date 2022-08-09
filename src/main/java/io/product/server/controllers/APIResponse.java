package io.product.server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class APIResponse<T>
{

	private LocalDateTime time;

	private String message;
	private HttpStatus status = HttpStatus.OK;
	private T data = null;

	public APIResponse(String message) {
		this.message = message;
		this.time = LocalDateTime.now();
	}

	public APIResponse<T> setStatus(HttpStatus status)
	{
		this.status = status;
		return this;
	}

	public APIResponse<T> setData(T data)
	{
		this.data = data;
		return this;
	}

	public String getMessage()
	{
		return message;
	}

	public int getStatus()
	{
		return status.value();
	}

	public T getData()
	{
		return data;
	}

	public ResponseEntity<Object> build()
	{

		Map<String, Object> map = new HashMap<>();
		if (this.data != null)
		{
			map.put("data", data);
		}
		map.put("status", status.value());
		map.put("message", message);
		map.put("timestamp",this.time);

		if (this.data instanceof Collection)
		{
			map.put("total", ((Collection<?>) this.data).size());
		}

		return new ResponseEntity<>(map, status);
	}
}
