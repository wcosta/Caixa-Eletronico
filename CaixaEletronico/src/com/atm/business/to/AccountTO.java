/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.business.to;

/**
 *
 * @author wcosta
 */
public class AccountTO {
    private int numAgency;
    private int numAccount;

    public AccountTO(int ag, int ct) {
        this.numAgency = ag;
        this.numAccount = ct;
    }
    
    public int getNumAgency() {
        return numAgency;
    }

    public void setNumAgency(int nuAgencia) {
        this.numAgency = nuAgencia;
    }

    public int getNumAccount() {
        return numAccount;
    }

    public void setNumAccount(int nuConta) {
        this.numAccount = nuConta;
    }
}
