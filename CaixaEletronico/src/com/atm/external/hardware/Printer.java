/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.external.hardware;

import com.atm.business.to.TransactionTO;
import com.atm.exception.HardwareException;

/**
 *
 * @author 71306587
 */
public class Printer {
    public String printTicket (TransactionTO to) throws HardwareException {
        if(to.getClient().getNumAgency() == 5) {
            throw new HardwareException (this);
        } else {
            Ticket comp = new Ticket(to);
            return comp.toString();
        }
    }
}
