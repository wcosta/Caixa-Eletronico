/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.exception;

import com.atm.business.to.TransactionTO;

/**
 *
 * @author 71306587
 */
public class TransactionException extends Exception {
    private TransactionTO dados;
    public static final String message = "Erro na transação";
    public TransactionException (TransactionTO to) {
        this.dados = to;
    }
}