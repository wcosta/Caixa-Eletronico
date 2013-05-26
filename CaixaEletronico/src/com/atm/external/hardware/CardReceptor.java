/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.external.hardware;

import com.atm.exception.HardwareException;
import com.atm.factory.ComponentFactory;
import com.atm.properties.PropertiesReader;
import java.io.IOException;

/**
 *
 * @author 71306587
 */
public class CardReceptor {
    PropertiesReader properties;
    public CardReceptor() throws IOException {
        properties = ComponentFactory.getPropertiesReaderInstance();
    }
    
    public void receiveCard() {
        System.out.println(properties.getMsg("msg.hardware.insert.card"));
    }
    
    public void removeCard() {
        System.out.println(properties.getMsg("msg.hardware.remove.card"));
    }
    public void blockCard () throws HardwareException {
        throw new HardwareException(properties.getMsg("err.password.card.blocked"));
    }
}
