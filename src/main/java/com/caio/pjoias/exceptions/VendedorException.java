package com.caio.pjoias.exceptions;


import java.util.function.Supplier;

public class VendedorException extends Exception {
    public VendedorException(String mensagem) {
        super(mensagem);
    }
}
