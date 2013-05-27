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
import com.atm.component.LogWriter;
import com.atm.component.PropertiesReader;
import com.atm.component.ScreenMessages;
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
    ScreenMessages screen;
    
    public AutomaticTellerMachine () throws IOException {
        transactionController = ComponentFactory.getTransactionControllerInstance();
        deviceController = ComponentFactory.getDeviceControllerInstance();
        logWriter = ComponentFactory.getLogWriterInstance();
        properties = ComponentFactory.getPropertiesReaderInstance();
        screen = ComponentFactory.getScreenMessagesInstance();
    }
    
    private BigDecimal avaiableValue;
    
    public void startAtm(int value) throws IOException{
        avaiableValue = new BigDecimal(value).setScale(2);
        logWriter.writeLog(properties.getMsg("msg.log.start") + " com " + avaiableValue + " reais");
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
            if(transaction.getTransactionType() == TransactionTO.TYPE_WITHDRAW) {
                updateAvaiableValue(transaction.getValue());
            }
            logWriter.writeLog(transaction);
            
            return transaction;
        } catch (ValidationException ex) {
            screen.showMessage(ex.getMessage());
            logWriter.writeLog(ex.getMessage());
        } catch (TransactionException ex) {
            screen.showMessage(ex.getMessage());
            logWriter.writeLog(ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            screen.showMessage(ex.getMessage());
            logWriter.writeLog(ex.getMessage());
        }
        return null;
    }
    
    public String printTicket(TransactionTO transaction) throws HardwareException, IOException{
        String ticket = "";
        try {
            ticket = deviceController.getPrinter().printTicket(transaction);
        } catch (HardwareException ex) {
            screen.showMessage(ex.getMessage());
            logWriter.writeLog(ex.getMessage());
        }
        return ticket;
    }
    
    public TransactionTO getInformations(TransactionTO to) throws Exception {
        TransactionTO newTO = to;
        Scanner in = new Scanner(System.in);
        switch (newTO.getTransactionType()) {
            case 2 :
                screen.showMessage(properties.getMsg("msg.menu.agency.destiny"));
                int agT = in.nextInt();
                screen.showMessage(properties.getMsg("msg.menu.account.destiny"));
                int accT = in.nextInt();
                newTO.setDestiny(new AccountTO(agT, accT));
                screen.showMessage(properties.getMsg("msg.menu.value.transfer"));
                int valorT = in.nextInt();
                newTO.setValue(new BigDecimal(valorT).setScale(2));
                break;
            case 3 :
                screen.showMessage(properties.getMsg("msg.menu.agency.destiny"));
                int agD = in.nextInt();
                screen.showMessage(properties.getMsg("msg.menu.account.destiny"));
                int accD = in.nextInt();
                newTO.setDestiny(new AccountTO(agD, accD));
                screen.showMessage(properties.getMsg("msg.menu.value.deposit"));
                int valorD = in.nextInt();
                newTO.setValue(new BigDecimal(valorD).setScale(2));
                
                deviceController.getEnvelopeReceptor().receiveEnvelope(newTO.getClient());
                break;
            case 4 :
                if(avaiableValue.intValue() < 10) {
                    screen.showMessage(properties.getMsg("err.draw.unavaiable"));
                    break;
                }
                screen.showMessage(properties.getMsg("msg.menu.value.draw"));
                int valorS = in.nextInt();
                boolean validValue = isValueAvaiable(valorS);
                if(validValue) {
                    newTO.setValue(new BigDecimal(valorS).setScale(2));
                } else {
                    while (validValue != true) {
                        screen.showMessage(properties.getMsg("err.draw.invalid.value.1") + avaiableValue + properties.getMsg("err.draw.invaild.value.2"));
                        valorS = in.nextInt();
                        validValue = isValueAvaiable(valorS);
                    }
                    newTO.setValue(new BigDecimal(valorS).setScale(2));
                }
                break;
            default :
                return newTO;
        }
        
        return newTO;
    }
    
    public boolean isValueAvaiable (int value){
        if(value <= avaiableValue.intValue()) return true;
        else return false;
    }
    
    public void updateAvaiableValue (BigDecimal value) {
        avaiableValue = avaiableValue.subtract(value);
    }

    public void turnOffAtm() throws IOException {
        logWriter.writeLog(properties.getMsg("msg.log.end"));
        logWriter.closeLog();
        System.exit(0);
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
    
    public ScreenMessages getScreen() {
        return screen;
    }
}
