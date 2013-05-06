/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.controller.impl;

import com.atm.business.to.AccountTO;
import com.atm.business.to.TransactionTO;
import com.atm.controller.TransactionController;
import com.atm.external.bank.Bank;
import java.math.BigDecimal;

/**
 *
 * @author 71306587
 */
public class TransactionControllerImpl implements TransactionController{    
    @Override
    public boolean validarSenha(AccountTO conta){
        return Bank.getInstance().validarSenha(conta);
    }
    
    @Override
    public boolean validarSessao(AccountTO conta){
        return Bank.getInstance().validarSessao(conta);
    }

    @Override
    public BigDecimal retornarSaldo(TransactionTO to) {
        return Bank.getInstance().retornarSaldo(to);
    }

    @Override
    public boolean aprovarDeposito(TransactionTO to) {
        return Bank.getInstance().aprovarDeposito(to);
    }

    @Override
    public boolean aprovarTransferencia(TransactionTO to) {
        return Bank.getInstance().aprovarTransferencia(to);
    }

    @Override
    public boolean aprovarSaque(TransactionTO to) {
        return Bank.getInstance().aprovarSaque(to);
    }
}
