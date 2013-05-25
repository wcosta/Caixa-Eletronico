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
import com.atm.factory.ControllerFactory;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author 71306587
 */
public class Atm {
    public static void main(String args[]){
        boolean loop = true;
        Scanner in = new Scanner(System.in);
        int num = 0;
        while(loop){
            System.out.println("Selecione a sua conta:"
                    + "\n1 - Agência 1 | Conta 100 - Tudo sucesso"
                    + "\n2 - Agência 2 | Conta 101 - Erro senha"
                    + "\n3 - Agência 3 | Conta 102 - Erro validação"
                    + "\n4 - Agência 4 | Conta 103 - Erro transação"
                    + "\n5 - Agência 5 | Conta 104 - Erro equipamento");
            num = in.nextInt();
            
            if(num >= 1 && num < 6) {
                loop = false;
            } else {
                System.out.println("Opção inválida.");
            }
        }
        
        loop = true;
        TransactionTO transacao = Helper.getTransactionTO(num);
        TransactionController transactionController = ControllerFactory.getTCInstance();
        DeviceController deviceController = ControllerFactory.getDCInstance();
        
        deviceController.getCardReceptor().receiveCard();
        
        String senha = "";
        int tentativas = 0;
        while(true) {
            try {
                if(tentativas == 0) {
                    System.out.println("Digite sua senha.");
                } else if(tentativas >= 3) {
                    deviceController.getCardReceptor().blockCard();
                }else {
                    System.out.println("Senha incorreta, tente novamente (Tentativa " + 
                            (tentativas + 1) + ")");
                }
                senha = in.next();
                if(transactionController.validatePassword(transacao.getClient())){
                    break;
                }
            } catch (ValidationException ex) {
                tentativas++;
            } catch (HardwareException ex) {
                System.out.println(ex.getMessage());
                System.exit(0);
            }
        }
        while(loop) {
            System.out.println("Selecione a operação desejada:"
                    + "\n1 - Consultar Saldo"
                    + "\n2 - Transferir"
                    + "\n3 - Depositar"
                    + "\n4 - Sacar"
                    + "\n0 - Sair");

            num = in.nextInt();
            if(num == 0) {
                break;
            }else if(num >= 1 && num < 5) {
                loop = false;
            } else {
                System.out.println("Opção inválida.");
            }
        }
        loop = true;
        transacao.setTransactionType(num);
        try {
            transactionController.validateSession(transacao.getClient());
            transacao = getInformations(transacao);
            transacao.setBalance(transactionController.consultBalance(transacao).setScale(2));
            transacao = transactionController.realizeTransaction(transacao);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        System.out.println(processResults(transacao));
        
        try {
            System.out.println(deviceController.getPrinter().printTicket(transacao));
        } catch (HardwareException ex) {
            System.out.println(ex.getMessage());
        }
        
        deviceController.getCardReceptor().removeCard();
                
    }
    
    public static TransactionTO getInformations(TransactionTO to) {
        TransactionTO newTO = to;
        Scanner in = new Scanner(System.in);
        switch (newTO.getTransactionType()) {
            case 2 :
                System.out.println("\nDigite a agência destino:");
                int agT = in.nextInt();
                System.out.println("\nDigite a conta destino");
                int accT = in.nextInt();
                newTO.setDestiny(new AccountTO(agT, accT));
                System.out.println("\nDigite o valor em reais a ser transferido");
                int valorT = in.nextInt();
                newTO.setValue(new BigDecimal(valorT).setScale(2));
                break;
            case 3 :
                System.out.println("\nDigite a agência destino");
                int agD = in.nextInt();
                System.out.println("\nDigite a conta destino");
                int accD = in.nextInt();
                newTO.setDestiny(new AccountTO(agD, accD));
                System.out.println("\nDigite o valor em reais a ser depositado");
                int valorD = in.nextInt();
                newTO.setValue(new BigDecimal(valorD).setScale(2));
                break;
            case 4 :
                System.out.println("\nDigite o valor em reais a ser sacado");
                int valorS = in.nextInt();
                newTO.setValue(new BigDecimal(valorS).setScale(2));
                break;
            default :
                return newTO;
        }
        
        return newTO;
    }
    
    public static String processResults (TransactionTO to) {
        String texto = "";
        
        switch (to.getTransactionType()) {
            case 1 :
                texto = "\nSaldo atual: " + to.getBalance() + "\n";
                break;
            case 2 :
                texto = "\nTransferência realizada com sucesso!\n";
                break;
            case 3 :
                texto = "\nDepósito realizado com sucesso!\n";
                break;
            case 4 :
                texto = "\nSaque realizado com sucesso!\n";
                break;
            default :
                return null;
        }
        
        return texto;
    }
}
