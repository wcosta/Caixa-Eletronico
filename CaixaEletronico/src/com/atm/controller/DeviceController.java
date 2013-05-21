/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.controller;

import com.atm.controller.impl.DeviceControllerImpl;
import com.atm.external.hardware.CardReceptor;
import com.atm.external.hardware.Dispenser;
import com.atm.external.hardware.EnvelopeReceptor;
import com.atm.external.hardware.Printer;

/**
 *
 * @author 71306587
 */
public interface DeviceController {
    public CardReceptor getCardReceptor();
    public Dispenser getDispenser();
    public EnvelopeReceptor getEnvelopeReceptor();
    public Printer getPrinter();
}
