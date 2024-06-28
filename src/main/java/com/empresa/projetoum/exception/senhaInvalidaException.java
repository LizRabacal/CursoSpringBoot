package com.empresa.projetoum.exception;

public class senhaInvalidaException  extends RuntimeException{

    public senhaInvalidaException(){
        super("Senha inválida");
    }
    
}
