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
    private String message;
    public static final String standardMessage = "Erro na transação: ";
    public TransactionException (TransactionTO to) {
        this.dados = to;
    }
    public TransactionException (String msg) {
        message = msg;
    }
    
    @Override
    public String getMessage(){
        if(dados == null) {
            return message;
        }
        return standardMessage + getTransactionDescription(dados.getTransactionType());
    }
    
    public String getTransactionDescription (int type) {
        String desc = "";
        switch (type) {
            case TransactionTO.TYPE_BALANCE:
                desc = "Consultar Saldo";
                break;
            
            case TransactionTO.TYPE_DEPOSIT:
                desc = "Depositar";
                break;
            
            case TransactionTO.TYPE_TRANSFER:
                desc = "Transferir";
                break;
            
            case TransactionTO.TYPE_WITHDRAW:
                desc = "Sacar";
                break;
            
            default:
                break;
        }
        return desc;
    }
}