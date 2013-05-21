/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.exception;

import com.atm.external.hardware.CardReceptor;
import com.atm.external.hardware.Dispenser;
import com.atm.external.hardware.EnvelopeReceptor;
import com.atm.external.hardware.Printer;
/**
 *
 * @author 71306587
 */
public class HardwareException extends Exception {
    private String message;
    public HardwareException (Object obj) {
        if(obj instanceof EnvelopeReceptor) {
            this.message = "Erro no equipamento: receptor de envelopes";
        } else if (obj instanceof CardReceptor) {
            this.message = "Erro no equipamento: receptor de cartão";
        } else if (obj instanceof Dispenser) {
            this.message = "Erro no equipamento: dispenser";
        } else if (obj instanceof Printer) {
            this.message = "Erro no equipamento: impressora";
        } else if (obj instanceof String) {
            this.message = (String) obj;
        }
    }
    
    @Override
    public String getMessage(){
        return this.message;
    }
}
