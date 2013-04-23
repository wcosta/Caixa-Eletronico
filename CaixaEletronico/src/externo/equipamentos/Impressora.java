/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package externo.equipamentos;

import negocio.to.TransacaoTO;

/**
 *
 * @author 71306587
 */
public class Impressora {
    public String imprimirComprovante (TransacaoTO to, int tipoComprovante) {
        Comprovante comp = new Comprovante(to, tipoComprovante);
        return comp.toString();
    }
}
