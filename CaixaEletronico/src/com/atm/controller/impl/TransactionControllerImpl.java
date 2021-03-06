/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.controller.impl;

import com.atm.business.to.AccountTO;
import com.atm.business.to.TransactionTO;
import com.atm.controller.TransactionController;
import com.atm.exception.TransactionException;
import com.atm.exception.ValidationException;
import test.external.Bank;
import com.atm.transaction.Consult;
import com.atm.transaction.Deposit;
import com.atm.transaction.Draw;
import com.atm.transaction.Transfer;
import java.math.BigDecimal;

/**
 *
 * @author 71306587
 */
public final class TransactionControllerImpl implements TransactionController{  
    @Override
    public boolean validatePassword(AccountTO conta) throws ValidationException{
        return Bank.getInstance().validatePassword(conta);
    }
    
    @Override
    public boolean validateSession(AccountTO conta) throws ValidationException{
        return Bank.getInstance().validateSession(conta);
    }
    
    @Override
    public BigDecimal consultBalance(TransactionTO to)  throws TransactionException {
        return Consult.consultBalance(to);
    }

    @Override
    public TransactionTO realizeTransaction(TransactionTO to) throws TransactionException {
        switch (to.getTransactionType()) {
            case 2 :
                return Transfer.transfer(to);
            case 3 :
                return Deposit.depositValue(to);
            case 4 :
                return Draw.draw(to);
        }
        return to;
    }
}
