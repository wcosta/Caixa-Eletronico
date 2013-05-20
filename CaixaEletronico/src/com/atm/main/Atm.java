/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.main;

import com.atm.business.to.TransactionTO;
import com.atm.controller.TransactionController;
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
        System.out.println("Selecione a sua conta:"
                + "\n1 - Agência 1 | Conta 100 - Tudo sucesso"
                + "\n2 - Agência 2 | Conta 101 - Erro senha"
                + "\n3 - Agência 3 | Conta 102 - Erro validação"
                + "\n4 - Agência 4 | Conta 103 - Erro transação"
                + "\n5 - Agência 5 | Conta 104 - Erro equipamento");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        TransactionTO transacao = Helper.getTransactionTO(n);
        String senha = in.nextLine();
        TransactionController transactionController = ControllerFactory.getTCInstance();
        try {
            transactionController.validatePassword(transacao.getClient());
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        
        System.out.println("Selecione a operação desejada:"
                + "\n1 - Consultar Saldo"
                + "\n2 - Transferir"
                + "\n3 - Depositar"
                + "\n4 - Sacar"
                + "\n0 - Sair");
        
        n = in.nextInt();
        
        transacao.setTransactionType(n);
        Object obj = null;
        try {
            transactionController.validateSession(transacao.getClient());
            obj = transactionController.realizeTransaction(transacao);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        System.out.println(processResults(transacao, obj));
    }
    
    public static String processResults (TransactionTO to, Object obj) {
        String texto = "";
        
        switch (to.getTransactionType()) {
            case 1 :
                texto = "Saldo atual: " + (BigDecimal) obj + "\n";
                break;
            case 2 :
                texto = "Transferência realizada com sucesso!";
                break;
            case 3 :
                texto = "Depósito realizado com sucesso!";
                break;
            case 4 :
                texto = "Saque realizado com sucesso!";
                break;
            default :
                return null;
        }
        
        return texto;
    }
}
