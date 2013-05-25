/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.factory;

import com.atm.controller.DeviceController;
import com.atm.controller.TransactionController;
import com.atm.controller.impl.DeviceControllerImpl;
import com.atm.controller.impl.TransactionControllerImpl;
import com.atm.log.LogWriter;
import java.io.IOException;

/**
 *
 * @author 71306587
 */
public class ComponentFactory {
    private static TransactionController transactionController;
    private static DeviceController deviceController;
    private static LogWriter logWriter;
    
    public static TransactionController getTransactionControllerInstance(){
        if(transactionController == null) {
            transactionController = new TransactionControllerImpl();
        }
        return transactionController;
    }
    
    public static DeviceController getDeviceControllerInstance(){
        if(deviceController == null) {
            deviceController = new DeviceControllerImpl();
        }
        return deviceController;
    }
    
    public static LogWriter getLogWriterInstance() throws IOException {
        if(logWriter == null) {
            logWriter = new LogWriter();
        }
        return logWriter;
    }
}
