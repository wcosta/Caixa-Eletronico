/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.main;

import com.atm.AutomaticTellerMachine;
import com.atm.business.to.AccountTO;
import com.atm.business.to.TransactionTO;
import com.atm.controller.DeviceController;
import com.atm.controller.TransactionController;
import com.atm.exception.HardwareException;
import com.atm.exception.ValidationException;
import test.external.Helper;
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
        AutomaticTellerMachine atm = new AutomaticTellerMachine();
        boolean loop = true;
        Scanner in = new Scanner(System.in);
        int num = 0;
        
        atm.startAtm(10000);
        
        while (true) {
            System.out.println(atm.getProperties().getMsg("msg.menu.1"));
            num = in.nextInt();

            if(num >= 0 && num < 6) {
                loop = false;
            } else {
                System.out.println(atm.getProperties().getMsg("err.invalid.option"));
            }
            if(num == 0)
                atm.turnOffAtm();
            
            loop = true;
            
            atm.receiveCard();
            TransactionTO transacao = Helper.getTransactionTO(num);
            boolean senhaCorreta = false;
            String senha = "";
            int tentativas = 0;
            while(loop) {
                try {
                    if(tentativas == 0) {
                        System.out.println(atm.getProperties().getMsg("msg.menu.password"));
                    } else if(tentativas >= 3) {
                        atm.blockCard();
                    }else {
                        System.out.println(atm.getProperties().getMsg("err.password.1") + 
                                (tentativas + 1) + atm.getProperties().getMsg("err.password.2"));
                    }
                    senha = in.next();
                    if(atm.getTransactionController().validatePassword(transacao.getClient())){
                        senhaCorreta = true;
                        break;
                    }
                } catch (ValidationException ex) {
                    tentativas++;
                } catch (HardwareException ex) {
                    System.out.println(ex.getMessage());
                    atm.getLogWriter().writeLog(ex.getMessage());
                    loop = false;
                }
            }
            if(senhaCorreta) {
                while(true) {
                    while(loop) {
                        System.out.println(atm.getProperties().getMsg("msg.menu.2"));

                        num = in.nextInt();
                        if(num >= 0 && num < 5) {
                            loop = false;
                        } else {
                            System.out.println(atm.getProperties().getMsg("err.invalid.option"));
                        }
                    }
                    if(num != 0) {
                        loop = true;
                        transacao.setTransactionType(num);
                        try {
                            transacao = atm.startProcess(transacao);
                            System.out.println(atm.printTicket(transacao));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        
                    } else {
                        atm.removeCard();
                        break;
                    }
                }
            }
        }
    }
}
