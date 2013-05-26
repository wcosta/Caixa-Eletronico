/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.main;

import com.atm.business.to.AccountTO;
import com.atm.business.to.TransactionTO;
import com.atm.controller.DeviceController;
import com.atm.controller.TransactionController;
import com.atm.exception.HardwareException;
import com.atm.exception.ValidationException;
import com.atm.external.bank.Helper;
import com.atm.factory.ComponentFactory;
import com.atm.log.LogWriter;
import com.atm.properties.PropertiesReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author 71306587
 */
public class Atm {
    public static void main(String args[]) throws IOException {
        TransactionController transactionController = ComponentFactory.getTransactionControllerInstance();
        DeviceController deviceController = ComponentFactory.getDeviceControllerInstance();
        LogWriter logWriter = ComponentFactory.getLogWriterInstance();
        PropertiesReader properties = ComponentFactory.getPropertiesReaderInstance();
        boolean loop = true;
        Scanner in = new Scanner(System.in);
        int num = 0;
        logWriter.writeLog(properties.getMsg("msg.log.start"));
        
        while (true) {
            System.out.println(properties.getMsg("msg.menu.1"));
            num = in.nextInt();

            if(num >= 0 && num < 6) {
                loop = false;
            } else {
                System.out.println(properties.getMsg("err.invalid.option"));
            }
            if(num == 0)
                turnOffAtm();
            
            loop = true;
            
            deviceController.getCardReceptor().receiveCard();
            TransactionTO transacao = Helper.getTransactionTO(num);
            boolean senhaCorreta = false;
            String senha = "";
            int tentativas = 0;
            while(loop) {
                try {
                    if(tentativas == 0) {
                        System.out.println(properties.getMsg("msg.menu.password"));
                    } else if(tentativas >= 3) {
                        deviceController.getCardReceptor().blockCard();
                    }else {
                        System.out.println(properties.getMsg("err.password.1") + 
                                (tentativas + 1) + properties.getMsg("err.password.2"));
                    }
                    senha = in.next();
                    if(transactionController.validatePassword(transacao.getClient())){
                        senhaCorreta = true;
                        break;
                    }
                } catch (ValidationException ex) {
                    tentativas++;
                } catch (HardwareException ex) {
                    System.out.println(ex.getMessage());
                    logWriter.writeLog(ex.getMessage());
                    loop = false;
                }
            }
            if(senhaCorreta) {
                while(true) {
                    while(loop) {
                        System.out.println(properties.getMsg("msg.menu.2"));

                        num = in.nextInt();
                        if(num >= 0 && num < 5) {
                            loop = false;
                        } else {
                            System.out.println(properties.getMsg("err.invalid.option"));
                        }
                    }
                    if(num != 0) {
                        loop = true;
                        transacao.setTransactionType(num);
                        try {
                            transactionController.validateSession(transacao.getClient());
                            transacao = getInformations(transacao);
                            transacao.setBalance(transactionController.consultBalance(transacao).setScale(2));
                            transacao = transactionController.realizeTransaction(transacao);
                            logWriter.writeLog(transacao);

                            try {
                                System.out.println(deviceController.getPrinter().printTicket(transacao));
                            } catch (HardwareException ex) {
                                System.out.println(ex.getMessage());
                                logWriter.writeLog(ex.getMessage());
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                            logWriter.writeLog(ex.getMessage());
                        }
                    } else {
                        deviceController.getCardReceptor().removeCard();
                        break;
                    }
                }
            }
        }
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

    public static void turnOffAtm() throws IOException {
        PropertiesReader properties = ComponentFactory.getPropertiesReaderInstance();
        ComponentFactory.getLogWriterInstance().writeLog(properties.getMsg("msg.log.end"));
        ComponentFactory.getLogWriterInstance().closeLog();
        System.exit(0);
    }
}
