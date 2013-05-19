/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.controller;

import com.atm.business.to.AccountTO;
import com.atm.business.to.TransactionTO;
import com.atm.exception.TransactionException;
import com.atm.exception.ValidationException;
import java.math.BigDecimal;

/**
 *
 * @author wcosta
 */
public interface TransactionController {
    public boolean validatePassword(AccountTO conta) throws ValidationException;
    public boolean validateSession(AccountTO conta) throws ValidationException;
    public BigDecimal getBalance (TransactionTO to) throws TransactionException;
    public boolean approveDeposit (TransactionTO to) throws TransactionException;
    public boolean approveTransfer (TransactionTO to) throws TransactionException;
    public boolean approveWithdraw (TransactionTO to) throws TransactionException;
}
