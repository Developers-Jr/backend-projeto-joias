package com.caio.pjoias.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> erros = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((erro) -> {
            var nomeCampo = ((FieldError) erro).getField();
            var mensagemErro = erro.getDefaultMessage();
            erros.put(nomeCampo, mensagemErro);
        });

        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(value=VendedorException.class)
    public ResponseEntity<String> handleVendedorException(VendedorException vendedorException) {

        return ResponseEntity.badRequest().body(vendedorException.getMessage());
    }
}
