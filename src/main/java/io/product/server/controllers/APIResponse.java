package io.product.server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class APIResponse
{

	private String message;
	private HttpStatus status = HttpStatus.OK;
	private Object data = null;

	public APIResponse(String message) {
		this.message = message;
	}

	public APIResponse setStatus(HttpStatus status)
	{
		this.status = status;
		return this;
	}

	public APIResponse setData(Object data)
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

	public Object getData()
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

		if (this.data instanceof Collection)
		{
			map.put("total", ((Collection<?>) this.data).size());
		}

		return new ResponseEntity<>(map, status);
	}
}
