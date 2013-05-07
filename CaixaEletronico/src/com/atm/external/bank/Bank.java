package com.atm.external.bank;

import com.atm.business.to.AccountTO;
import com.atm.business.to.TransactionTO;
import com.atm.exception.TransactionException;
import java.math.BigDecimal;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author 71306587
 */
public class Bank {
    /*
     * Instância única da classe banco
     */
    private static Bank instance;
    /*
     * Retorna instância única de banco
     * @return instância de banco
     */
    public static Bank getInstance(){
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }
    public boolean validatePassword (AccountTO conta) {
        if(conta.getNumAgency() != 2){
            return true;
        }else{
            return false;
        }
    }
    public boolean validateSession (AccountTO conta) {
        if(conta.getNumAgency() != 3){
            return true;
        }else{
            return false;
        }
    }
    public BigDecimal getBalance (TransactionTO to){
        if(to.getBalance() != null) {
            return to.getBalance();
        }
        BigDecimal saldo;
        try {
            switch(to.getClient().getNumAgency()) {
                case 1:
                    saldo = new BigDecimal(1000).setScale(2);
                    break;
                case 2:
                    saldo = new BigDecimal(2000).setScale(2);
                    break;
                case 3:
                    saldo = new BigDecimal(3000).setScale(2);
                    break;
                case 4:
                    throw new TransactionException(to);
                case 5:
                    saldo = new BigDecimal(5000).setScale(2);
                    break;
                default:
                    saldo = new BigDecimal(0).setScale(2);
                    break;
            }
            return saldo;
        } catch (TransactionException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public boolean approveDeposit (TransactionTO to) {
        if(to.getClient().getNumAgency() == 4) {
            return false;
        } else {
            return true;
        }
    }
    public boolean approveTransfer (TransactionTO to) {
        if(to.getClient().getNumAgency() == 4) {
            return false;
        } else {
            return true;
        }
    }
    public boolean approveWithdraw (TransactionTO to) {
        if(to.getClient().getNumAgency() == 4) {
            return false;
        } else {
            return true;
        }
    }
}
