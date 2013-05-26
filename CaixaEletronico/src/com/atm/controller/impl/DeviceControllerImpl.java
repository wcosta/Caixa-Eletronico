/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.controller.impl;

import com.atm.controller.DeviceController;
import com.atm.external.hardware.CardReceptor;
import com.atm.external.hardware.Dispenser;
import com.atm.external.hardware.EnvelopeReceptor;
import com.atm.external.hardware.Printer;
import java.io.IOException;

/**
 *
 * @author 71306587
 */
public class DeviceControllerImpl implements DeviceController {    
    private CardReceptor cardReceptor;
    private Dispenser dispenser;
    private EnvelopeReceptor envelopeReceptor;
    private Printer printer;
    
    public DeviceControllerImpl() throws IOException{
        cardReceptor = new CardReceptor();
        dispenser = new Dispenser();
        envelopeReceptor = new EnvelopeReceptor();
        printer = new Printer();
    }
    
    @Override
    public CardReceptor getCardReceptor() {
        return cardReceptor;
    }

    @Override
    public Dispenser getDispenser() {
        return dispenser;
    }

    @Override
    public EnvelopeReceptor getEnvelopeReceptor() {
        return envelopeReceptor;
    }

    @Override
    public Printer getPrinter() {
        return printer;
    }
}
