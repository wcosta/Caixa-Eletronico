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

/**
 *
 * @author 71306587
 */
public class Dispenser {
    PropertiesReader properties;
    public Dispenser() throws IOException {
        properties = ComponentFactory.getPropertiesReaderInstance();
    }
    
    public void sendBallots(AccountTO acc) throws HardwareException{
        if(acc.getNumAgency() == 5) {
            throw new HardwareException(this);
        } else {
            System.out.println(properties.getMsg("msg.hardware.dispenser.money"));
        }
    }
}
