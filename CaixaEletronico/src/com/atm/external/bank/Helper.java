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
    public static final TransactionTO conta1 = new TransactionTO(new AccountTO(1,100), new AccountTO(10,200));
    /**
     * Erro senha
     */
    public static final TransactionTO conta2 = new TransactionTO(new AccountTO(2,101),new AccountTO(20,201));
    /**
     * Erro validação
     */
    public static final TransactionTO conta3 = new TransactionTO(new AccountTO(3,102),new AccountTO(30,202));
    /**
     * Erro todas transações
     */
    public static final TransactionTO conta4 = new TransactionTO(new AccountTO(4,103),new AccountTO(40,203));
    /**
     * Erro equipamento
     */
    public static final TransactionTO conta5 = new TransactionTO(new AccountTO(5,104),new AccountTO(50,204));
}
