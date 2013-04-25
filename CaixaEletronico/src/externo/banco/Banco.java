package externo.banco;

import java.math.BigDecimal;
import negocio.to.ContaTO;
import negocio.to.TransacaoTO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author 71306587
 */
public class Banco {
    /*
     * Instância única da classe banco
     */
    private static Banco instance;
    /*
     * Retorna instância única de banco
     * @return instância de banco
     */
    public static Banco getInstance(){
        if (instance == null) {
            instance = new Banco();
        }
        return instance;
    }
    public boolean validarSenha (ContaTO conta) {
        if(conta.getNuAgencia() != 2){
            return true;
        }else{
            return false;
        }
    }
    public boolean validarSessao (ContaTO conta) {
        if(conta.getNuAgencia() != 3){
            return true;
        }else{
            return false;
        }
    }
    public BigDecimal retornarSaldo (TransacaoTO to) {
        if(to.getSaldo() != null) {
            return to.getSaldo();
        }
        BigDecimal saldo;
        switch(to.getCliente().getNuAgencia()) {
            case 1:
                saldo = new BigDecimal(1000).setScale(2);
                break;
            case 2:
                saldo = new BigDecimal(2000).setScale(2);
                break;
            case 3:
                saldo = new BigDecimal(3000).setScale(2);
                break;
            case 4:
                saldo = new BigDecimal(4000).setScale(2);
                break;
            case 5:
                saldo = new BigDecimal(5000).setScale(2);
                break;
            default:
                saldo = new BigDecimal(0).setScale(2);
                break;
        }
        return saldo;
    }
    
    public void aprovarDeposito () {
    }
    public void aprovarTransferencia () {
    }
    public void aprovarSaque () {
    }
}
