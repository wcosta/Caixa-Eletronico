/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.external.hardware;

import com.atm.exception.HardwareException;

/**
 *
 * @author 71306587
 */
public class CardReceptor {
    public void receiveCard() {
        System.out.println("Cartão inserido.");
    }
    
    public void removeCard() {
        System.out.println("Remova o cartão.");
    }
    public void blockCard () throws HardwareException {
        throw new HardwareException("Terceira tentativa de senha incorreta!\n"
                + "Seu cartão foi bloqueado!");
    }
}
