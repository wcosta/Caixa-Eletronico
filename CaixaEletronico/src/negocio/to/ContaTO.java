/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.to;

/**
 *
 * @author wcosta
 */
public class ContaTO {
    private int nuAgencia;
    private int nuConta;

    public ContaTO(int ag, int ct) {
        this.nuAgencia = ag;
        this.nuConta = ct;
    }
    
    public int getNuAgencia() {
        return nuAgencia;
    }

    public void setNuAgencia(int nuAgencia) {
        this.nuAgencia = nuAgencia;
    }

    public int getNuConta() {
        return nuConta;
    }

    public void setNuConta(int nuConta) {
        this.nuConta = nuConta;
    }
}
