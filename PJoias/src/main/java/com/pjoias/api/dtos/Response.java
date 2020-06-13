package com.pjoias.api.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Response<T> {
	@Getter @Setter private T data;
	
	private List<String> errors = new ArrayList<>();
	
	public void addError(String errorMessage) {
		errors.add(errorMessage);
	}
	
	public void addAllErrors(List<String> errorMessage) {
		errors.addAll(errorMessage);
	}
	
	public List<String> getErrors() {
		return this.errors;
	}
}
