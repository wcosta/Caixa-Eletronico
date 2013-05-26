/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.transaction;

import com.atm.business.to.TransactionTO;
import com.atm.exception.TransactionException;
import test.external.Bank;

/**
 *
 * @author Walter
 */
public class Transfer {
    public static TransactionTO transfer(TransactionTO to) throws TransactionException {
        return Bank.getInstance().approveTransfer(to);
    }
}
