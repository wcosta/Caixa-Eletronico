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
    private TransactionTO dados;
    private int tipoComprovante;
    
    public Ticket(TransactionTO to, int tipoComprovante) { 
        dados = to;
        this.tipoComprovante = tipoComprovante;
    }
    
    private final String cabecalho = "----------------------------\n"
            + "Data: " + getDataAtual() + "\n"
            + "Localidade: Mackenzie - São Paulo - SP\n"
            + "----------------------------\n"
            + "Agência: " + getDados().getCliente().getNuAgencia() 
            + " | Conta: " + getDados().getCliente().getNuConta()
            + "----------------------------\n";
    private final String rodape = "----------------------------\n";
    
    public String getDataAtual(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        return sdf.format(new Date());
    }
    
    @Override
    public String toString(){
        String comprovante = "";
        comprovante += this.cabecalho;
        switch (this.tipoComprovante){
            case 1:
                comprovante += "Saldo atual: " + getDados().getSaldo() + "\n";
                break;
            case 2:
                comprovante += "Saldo atual: " + getDados().getSaldo()
                        + "\nAgência Favorecido: " + getDados().getDestino().getNuAgencia()
                        + "\nConta Favorecido: " + getDados().getDestino().getNuConta()
                        + "\nValor transferido: " + getDados().getValor() + "\n";
                break;
            case 3:
                comprovante += "Agência Favorecido: " + getDados().getDestino().getNuAgencia()
                        + "\nConta Favorecido: " + getDados().getDestino().getNuConta()
                        + "\nValor depositado: " + getDados().getValor() + "\n";
                break;
            case 4:
                comprovante += "Saldo atual: " + getDados().getSaldo() 
                        + "\nValor sacado: " + getDados().getValor() + "\n";
                break;
            default:
                break;
        }
        comprovante += this.rodape;
        return comprovante;
    }

    public TransactionTO getDados() {
        return dados;
    }

    public void setDados(TransactionTO dados) {
        this.dados = dados;
    }
    
    
}
