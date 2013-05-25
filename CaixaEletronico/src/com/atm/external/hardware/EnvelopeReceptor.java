/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.external.hardware;

import com.atm.business.to.AccountTO;
import com.atm.exception.HardwareException;

/**
 *
 * @author 71306587
 */
public class EnvelopeReceptor {
    public void receiveEnvelope(AccountTO acc) throws HardwareException{
        if(acc.getNumAgency() == 5) {
            throw new HardwareException(this);
        } else {
            System.out.println("Insira o envelope.\nEnvelope recebido.");
        }
    }
}
