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
import com.atm.external.bank.Bank;
import java.math.BigDecimal;

/**
 *
 * @author 71306587
 */
public class TransactionControllerImpl implements TransactionController{    
    @Override
    public boolean validatePassword(AccountTO conta) throws ValidationException{
        return Bank.getInstance().validatePassword(conta);
    }
    
    @Override
    public boolean validateSession(AccountTO conta) throws ValidationException{
        return Bank.getInstance().validateSession(conta);
    }

    @Override
    public BigDecimal getBalance(TransactionTO to) throws TransactionException {
        return Bank.getInstance().getBalance(to);
    }

    @Override
    public boolean approveDeposit(TransactionTO to) throws TransactionException{
        return Bank.getInstance().approveDeposit(to);
    }

    @Override
    public boolean approveTransfer(TransactionTO to) throws TransactionException {
        return Bank.getInstance().approveTransfer(to);
    }

    @Override
    public boolean approveWithdraw(TransactionTO to) throws TransactionException {
        return Bank.getInstance().approveWithdraw(to);
    }
}
