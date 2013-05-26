/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm;

import com.atm.business.to.TransactionTO;
import com.atm.controller.DeviceController;
import com.atm.controller.TransactionController;
import com.atm.exception.HardwareException;
import com.atm.exception.TransactionException;
import com.atm.exception.ValidationException;
import com.atm.factory.ComponentFactory;
import com.atm.log.LogWriter;
import static test.main.Atm.getInformations;
import com.atm.properties.PropertiesReader;
import java.io.IOException;
import java.math.BigDecimal;

/**
 *
 * @author Walter
 */
public class AutomaticTellerMachine {
    TransactionController transactionController;
    DeviceController deviceController;
    LogWriter logWriter;
    PropertiesReader properties;
    
    public AutomaticTellerMachine () throws IOException {
        transactionController = ComponentFactory.getTransactionControllerInstance();
        deviceController = ComponentFactory.getDeviceControllerInstance();
        logWriter = ComponentFactory.getLogWriterInstance();
        properties = ComponentFactory.getPropertiesReaderInstance();
    }
    
    private BigDecimal avaiableValue;
    
    public void startAtm(int value) throws IOException{
        avaiableValue = new BigDecimal(value).setScale(2);
        logWriter.writeLog(properties.getMsg("msg.log.start"));
    }
    
    public void receiveCard() {
        deviceController.getCardReceptor().receiveCard();
    }
    
    public void blockCard() throws HardwareException {
        deviceController.getCardReceptor().blockCard();
    }
    
    public void removeCard() {
        deviceController.getCardReceptor().removeCard();
    }
    
    public void startProcess(TransactionTO transaction) throws IOException, ValidationException, TransactionException{
        try {
            transactionController.validateSession(transaction.getClient());
            transaction = getInformations(transaction);
            transaction.setBalance(transactionController.consultBalance(transaction).setScale(2));
            transaction = transactionController.realizeTransaction(transaction);
            logWriter.writeLog(transaction);
        } catch (Exception ex) {
            logWriter.writeLog(ex.getMessage());
        }
    }
    
    public void printTicket(TransactionTO transaction) throws HardwareException, IOException{
        try {
            deviceController.getPrinter().printTicket(transaction);
        } catch (HardwareException ex) {
            System.out.println(ex.getMessage());
            logWriter.writeLog(ex.getMessage());
        }
    }
    
    

    public void turnOffAtm() throws IOException {
        logWriter.writeLog(properties.getMsg("msg.log.end"));
        logWriter.closeLog();
    }

    public TransactionController getTransactionController() {
        return transactionController;
    }

    public DeviceController getDeviceController() {
        return deviceController;
    }

    public LogWriter getLogWriter() {
        return logWriter;
    }

    public PropertiesReader getProperties() {
        return properties;
    }
}
