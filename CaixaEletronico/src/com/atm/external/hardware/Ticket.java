/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.external.hardware;

import com.atm.business.to.TransactionTO;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 71306587
 */
public class Ticket {
    private TransactionTO data;
    private int ticketType;
    
    public Ticket(TransactionTO to, int ticketType) { 
        data = to;
        this.ticketType = ticketType;
    }
    
    private final String header = "----------------------------\n"
            + "Data: " + getCurrentDate() + "\n"
            + "Localidade: Mackenzie - São Paulo - SP\n"
            + "----------------------------\n"
            + "Agência: " + getDados().getClient().getNumAgency() 
            + " | Conta: " + getDados().getClient().getNumAccount()
            + "----------------------------\n";
    private final String footer = "----------------------------\n";
    
    public String getCurrentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        return sdf.format(new Date());
    }
    
    @Override
    public String toString(){
        String comprovante = "";
        comprovante += this.header;
        switch (this.ticketType){
            case 1:
                comprovante += "Saldo atual: " + getDados().getBalance() + "\n";
                break;
            case 2:
                comprovante += "Saldo atual: " + getDados().getBalance()
                        + "\nAgência Favorecido: " + getDados().getDestiny().getNumAgency()
                        + "\nConta Favorecido: " + getDados().getDestiny().getNumAccount()
                        + "\nValor transferido: " + getDados().getValue() + "\n";
                break;
            case 3:
                comprovante += "Agência Favorecido: " + getDados().getDestiny().getNumAgency()
                        + "\nConta Favorecido: " + getDados().getDestiny().getNumAccount()
                        + "\nValor depositado: " + getDados().getValue() + "\n";
                break;
            case 4:
                comprovante += "Saldo atual: " + getDados().getBalance() 
                        + "\nValor sacado: " + getDados().getValue() + "\n";
                break;
            default:
                break;
        }
        comprovante += this.footer;
        return comprovante;
    }

    public TransactionTO getDados() {
        return data;
    }

    public void setDados(TransactionTO dados) {
        this.data = dados;
    }
    
    
}
