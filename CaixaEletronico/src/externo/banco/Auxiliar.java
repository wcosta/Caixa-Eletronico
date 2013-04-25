/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package externo.banco;

import negocio.to.ContaTO;
import negocio.to.TransacaoTO;

/**
 *
 * @author wcosta
 */
public class Auxiliar {
    /**
     * Tudo sucesso
     */
    public static final TransacaoTO conta1 = new TransacaoTO(new ContaTO(1,100), new ContaTO(10,200));
    /**
     * Erro senha
     */
    public static final TransacaoTO conta2 = new TransacaoTO(new ContaTO(2,101),new ContaTO(20,201));
    /**
     * Erro validação
     */
    public static final TransacaoTO conta3 = new TransacaoTO(new ContaTO(3,102),new ContaTO(30,202));
    /**
     * Erro todas transações
     */
    public static final TransacaoTO conta4 = new TransacaoTO(new ContaTO(4,103),new ContaTO(40,203));
    /**
     * Erro equipamento
     */
    public static final TransacaoTO conta5 = new TransacaoTO(new ContaTO(5,104),new ContaTO(50,204));
}
