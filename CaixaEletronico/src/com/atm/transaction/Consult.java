/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.transaction;

import com.atm.business.to.TransactionTO;
import com.atm.exception.TransactionException;
import test.external.Bank;
import java.math.BigDecimal;

/**
 *
 * @author Walter
 */
public class Consult {
    public static BigDecimal consultBalance(TransactionTO to) throws TransactionException {
        return Bank.getInstance().getBalance(to);
    }
}
