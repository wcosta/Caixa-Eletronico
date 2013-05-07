/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.controller;

import com.atm.business.to.AccountTO;
import com.atm.business.to.TransactionTO;
import java.math.BigDecimal;

/**
 *
 * @author wcosta
 */
public interface TransactionController {
    public boolean validatePassword(AccountTO conta);
    public boolean validateSession(AccountTO conta);
    public BigDecimal getBalance (TransactionTO to);
    public boolean approveDeposit (TransactionTO to);
    public boolean approveTransfer (TransactionTO to);
    public boolean approveWithdraw (TransactionTO to);
}
