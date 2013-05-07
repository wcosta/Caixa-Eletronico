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
    public static final String message = "Erro na transação: ";
    public TransactionException (TransactionTO to) {
        this.dados = to;
    }
    
    @Override
    public String getMessage(){
        return message + getTransactionDescription(dados.getTransactionType());
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