/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.transaction;

import com.atm.business.to.TransactionTO;
import com.atm.exception.TransactionException;
import com.atm.external.bank.Bank;

/**
 *
 * @author Walter
 */
public class Draw {
    public static TransactionTO draw(TransactionTO to) throws TransactionException {
        return Bank.getInstance().approveWithdraw(to);
    }
}
