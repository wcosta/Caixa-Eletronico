/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.exception;

/**
 *
 * @author 71306587
 */
public class ValidationException extends Exception {
    private String message;
    public ValidationException (String tipoErro) {
        this.message = tipoErro;
    }
    @Override
    public String getMessage() {
        return this.message;
    }
}
