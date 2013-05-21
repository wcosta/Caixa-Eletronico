/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.factory;

import com.atm.controller.DeviceController;
import com.atm.controller.TransactionController;
import com.atm.controller.impl.DeviceControllerImpl;
import com.atm.controller.impl.TransactionControllerImpl;

/**
 *
 * @author 71306587
 */
public class ControllerFactory {
    private static TransactionController transactionController;
    private static DeviceController deviceController;
    
    public static TransactionController getTCInstance(){
        if(transactionController == null) {
            transactionController = new TransactionControllerImpl();
        }
        return transactionController;
    }
    
    public static DeviceController getDCInstance(){
        if(deviceController == null) {
            deviceController = new DeviceControllerImpl();
        }
        return deviceController;
    }
}
