/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm;

import com.atm.business.to.AccountTO;
import com.atm.business.to.TransactionTO;
import com.atm.controller.DeviceController;
import com.atm.controller.TransactionController;
import com.atm.exception.HardwareException;
import com.atm.exception.TransactionException;
import com.atm.exception.ValidationException;
import com.atm.factory.ComponentFactory;
import com.atm.log.LogWriter;
import com.atm.properties.PropertiesReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

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
    
    public TransactionTO startProcess(TransactionTO to) throws IOException, ValidationException, TransactionException{
        TransactionTO transaction = to;
        try {
            transactionController.validateSession(transaction.getClient());
            transaction = getInformations(transaction);
            transaction.setBalance(transactionController.consultBalance(transaction).setScale(2));
            transaction = transactionController.realizeTransaction(transaction);
            logWriter.writeLog(transaction);
        } catch (Exception ex) {
            logWriter.writeLog(ex.getMessage());
        }
        return transaction;
    }
    
    public String printTicket(TransactionTO transaction) throws HardwareException, IOException{
        String ticket = "";
        try {
            ticket = deviceController.getPrinter().printTicket(transaction);
        } catch (HardwareException ex) {
            System.out.println(ex.getMessage());
            logWriter.writeLog(ex.getMessage());
        }
        return ticket;
    }
    
    public static TransactionTO getInformations(TransactionTO to) throws IOException {
        PropertiesReader properties = ComponentFactory.getPropertiesReaderInstance();
        TransactionTO newTO = to;
        Scanner in = new Scanner(System.in);
        switch (newTO.getTransactionType()) {
            case 2 :
                System.out.println(properties.getMsg("msg.menu.agency.destiny"));
                int agT = in.nextInt();
                System.out.println(properties.getMsg("msg.menu.account.destiny"));
                int accT = in.nextInt();
                newTO.setDestiny(new AccountTO(agT, accT));
                System.out.println(properties.getMsg("msg.menu.value.transfer"));
                int valorT = in.nextInt();
                newTO.setValue(new BigDecimal(valorT).setScale(2));
                break;
            case 3 :
                System.out.println(properties.getMsg("msg.menu.agencia.destino"));
                int agD = in.nextInt();
                System.out.println(properties.getMsg("msg.menu.conta.destino"));
                int accD = in.nextInt();
                newTO.setDestiny(new AccountTO(agD, accD));
                System.out.println(properties.getMsg("msg.menu.value.deposit"));
                int valorD = in.nextInt();
                newTO.setValue(new BigDecimal(valorD).setScale(2));
                break;
            case 4 :
                System.out.println(properties.getMsg("msg.menu.value.draw"));
                int valorS = in.nextInt();
                newTO.setValue(new BigDecimal(valorS).setScale(2));
                break;
            default :
                return newTO;
        }
        
        return newTO;
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
