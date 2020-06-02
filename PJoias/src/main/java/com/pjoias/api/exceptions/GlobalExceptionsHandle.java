package com.pjoias.api.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pjoias.api.dtos.Response;

@ControllerAdvice
public class GlobalExceptionsHandle {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response<String>> excecaoGenerica() {
		Response<String> response = new Response<>();
		
		response.setData("Algum erro ocorreu");
		return ResponseEntity.badRequest().body(response);
	}

}
