package com.atm.external.bank;

import com.atm.business.to.AccountTO;
import com.atm.business.to.TransactionTO;
import java.math.BigDecimal;
import com.atm.exception.TransactionException;

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
    public boolean validarSenha (AccountTO conta) {
        if(conta.getNuAgencia() != 2){
            return true;
        }else{
            return false;
        }
    }
    public boolean validarSessao (AccountTO conta) {
        if(conta.getNuAgencia() != 3){
            return true;
        }else{
            return false;
        }
    }
    public BigDecimal retornarSaldo (TransactionTO to){
        if(to.getSaldo() != null) {
            return to.getSaldo();
        }
        BigDecimal saldo;
        try {
            switch(to.getCliente().getNuAgencia()) {
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
    
    public boolean aprovarDeposito (TransactionTO to) {
        if(to.getCliente().getNuAgencia() == 4) {
            return false;
        } else {
            return true;
        }
    }
    public boolean aprovarTransferencia (TransactionTO to) {
        if(to.getCliente().getNuAgencia() == 4) {
            return false;
        } else {
            return true;
        }
    }
    public boolean aprovarSaque (TransactionTO to) {
        if(to.getCliente().getNuAgencia() == 4) {
            return false;
        } else {
            return true;
        }
    }
}
