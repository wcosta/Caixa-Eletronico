/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.external.hardware;

import com.atm.business.to.TransactionTO;

/**
 *
 * @author 71306587
 */
public class Printer {
    public String imprimirComprovante (TransactionTO to, int tipoComprovante) {
        Ticket comp = new Ticket(to, tipoComprovante);
        return comp.toString();
    }
}
