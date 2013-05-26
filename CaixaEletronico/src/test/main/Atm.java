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
        atm.getLogWriter().writeLog(atm.getProperties().getMsg("msg.log.start"));
        
        while (true) {
            System.out.println(atm.getProperties().getMsg("msg.menu.1"));
            num = in.nextInt();

            if(num >= 0 && num < 6) {
                loop = false;
            } else {
                System.out.println(atm.getProperties().getMsg("err.invalid.option"));
            }
            if(num == 0)
                turnOffAtm();
            
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
                            atm.startProcess(transacao);
                            atm.printTicket(transacao);
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
