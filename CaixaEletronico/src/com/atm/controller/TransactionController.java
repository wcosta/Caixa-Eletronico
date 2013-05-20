/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.controller;

import com.atm.business.to.AccountTO;
import com.atm.business.to.TransactionTO;
import com.atm.exception.TransactionException;
import com.atm.exception.ValidationException;

/**
 *
 * @author wcosta
 */
public interface TransactionController {
    public boolean validatePassword(AccountTO conta) throws ValidationException;
    public boolean validateSession(AccountTO conta) throws ValidationException;
    public Object realizeTransaction(TransactionTO to) throws TransactionException;
}
