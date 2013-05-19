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

/**
 *
 * @author 71306587
 */
public class DeviceControllerImpl implements DeviceController {
    private static DeviceControllerImpl instance;
    
    private CardReceptor cardReceptor = new CardReceptor();
    private Dispenser dispenser = new Dispenser();
    private EnvelopeReceptor envelopeReceptor = new EnvelopeReceptor();
    private Printer printer = new Printer();
    
    @Override
    public DeviceControllerImpl getInstance() {
        if(instance == null) {
            instance =  new DeviceControllerImpl();
        }
        return instance;
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
