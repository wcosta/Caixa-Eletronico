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
    public boolean validarSenha(AccountTO conta);
    public boolean validarSessao(AccountTO conta);
    public BigDecimal retornarSaldo (TransactionTO to);
    public boolean aprovarDeposito (TransactionTO to);
    public boolean aprovarTransferencia (TransactionTO to);
    public boolean aprovarSaque (TransactionTO to);
}
