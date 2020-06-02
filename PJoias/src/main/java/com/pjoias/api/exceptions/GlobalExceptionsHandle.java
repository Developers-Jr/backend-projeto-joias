package com.pjoias.api.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pjoias.api.dtos.Response;

@ControllerAdvice
public class GlobalExceptionsHandle {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response<String>> excecaoGenerica(Exception e) {
		Response<String> response = new Response<>();
		
		response.addError("Algo deu errado! \nMessage: " + e.getMessage());
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Response<String>> notFoundException(NotFoundException e) {
		return ResponseEntity.notFound().build();
	}
}
