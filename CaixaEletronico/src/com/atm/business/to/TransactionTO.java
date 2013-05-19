/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.business.to;

import java.math.BigDecimal;

/**
 *
 * @author 71306587
 */
public class TransactionTO {
    private AccountTO client;
    private AccountTO destiny;
    private int transactionType;
    private BigDecimal value;
    private BigDecimal balance;
    
    public TransactionTO (AccountTO c1) {
        client = c1;
    }

    public AccountTO getClient() {
        return client;
    }

    public void setClient(AccountTO cliente) {
        this.client = cliente;
    }

    public AccountTO getDestiny() {
        return destiny;
    }

    public void setDestiny(AccountTO destino) {
        this.destiny = destino;
    }
    
    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int tipoTransacao) {
        this.transactionType = tipoTransacao;
    }
    
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal valor) {
        this.value = valor;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal saldo) {
        this.balance = saldo;
    }
    
    /**
     * Constantes
     */
    public static final int TYPE_BALANCE = 1;
    public static final int TYPE_TRANSFER = 2;
    public static final int TYPE_DEPOSIT = 3;
    public static final int TYPE_WITHDRAW = 4;
}
