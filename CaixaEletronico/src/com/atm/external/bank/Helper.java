/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.external.bank;

import com.atm.business.to.AccountTO;
import com.atm.business.to.TransactionTO;

/**
 *
 * @author wcosta
 */
public class Helper {
    /**
     * Tudo sucesso
     */
    public static final TransactionTO conta1 = new TransactionTO(new AccountTO(1,100));
    /**
     * Erro senha
     */
    public static final TransactionTO conta2 = new TransactionTO(new AccountTO(2,101));
    /**
     * Erro validação
     */
    public static final TransactionTO conta3 = new TransactionTO(new AccountTO(3,102));
    /**
     * Erro todas transações
     */
    public static final TransactionTO conta4 = new TransactionTO(new AccountTO(4,103));
    /**
     * Erro equipamento
     */
    public static final TransactionTO conta5 = new TransactionTO(new AccountTO(5,104));
    
    public static TransactionTO getTransactionTO(int nConta) {
        switch(nConta) {
            case 1: return conta1;
            case 2: return conta2;
            case 3: return conta3;
            case 4: return conta4;
            case 5: return conta5;
        }
        return null;
    }
}
