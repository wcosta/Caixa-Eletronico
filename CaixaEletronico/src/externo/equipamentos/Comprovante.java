/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package externo.equipamentos;

import java.text.SimpleDateFormat;
import java.util.Date;
import negocio.to.TransacaoTO;

/**
 *
 * @author 71306587
 */
public class Comprovante {
    private TransacaoTO dados;
    private int tipoComprovante;
    
    public Comprovante(TransacaoTO to, int tipoComprovante) { 
        dados = to;
        this.tipoComprovante = tipoComprovante;
    }
    
    private final String cabecalho = "----------------------------\n"
            + "Data: " + getDataAtual() + "\n"
            + "Localidade: Mackenzie - São Paulo - SP\n"
            + "----------------------------\n"
            + "Agência: " + getDados().getNuAgenciaCliente() 
            + " | Conta: " + getDados().getNuContaCliente()
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
                        + "\nAgência Favorecido: " + getDados().getNuAgenciaDestino()
                        + "\nConta Favorecido: " + getDados().getNuContaDestino()
                        + "\nValor transferido: " + getDados().getValor() + "\n";
                break;
            case 3:
                comprovante += "Agência Favorecido: " + getDados().getNuAgenciaDestino()
                        + "\nConta Favorecido: " + getDados().getNuContaDestino()
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

    public TransacaoTO getDados() {
        return dados;
    }

    public void setDados(TransacaoTO dados) {
        this.dados = dados;
    }
    
    
    public static final int tipoSaldo = 1;
    public static final int tipoTransferencia = 2;
    public static final int tipoDeposito = 3;
    public static final int tipoSaque = 4;
}
