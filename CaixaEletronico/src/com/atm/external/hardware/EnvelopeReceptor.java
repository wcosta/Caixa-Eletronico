/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.external.hardware;

import com.atm.business.to.AccountTO;
import com.atm.exception.HardwareException;
import com.atm.factory.ComponentFactory;
import com.atm.component.PropertiesReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 71306587
 */
public class EnvelopeReceptor {
    PropertiesReader properties;
    public EnvelopeReceptor() throws IOException {
        properties = ComponentFactory.getPropertiesReaderInstance();
    }
    public void receiveEnvelope(AccountTO acc) throws HardwareException{
        if(acc.getNumAgency() == 5) {
            throw new HardwareException(this);
        } else {
            System.out.println(properties.getMsg("msg.hardware.envelope.receive"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(EnvelopeReceptor.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(properties.getMsg("msg.hardware.envelope.confirmation"));
        }
    }
}
