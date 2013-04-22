/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.controller;

import negocio.to.TransacaoTO;

/**
 *
 * @author 71306587
 */
public class TransacaoController {
    private TransacaoTO dados;
    
    

    public TransacaoTO getDados() {
        return dados;
    }

    public void setDados(TransacaoTO dados) {
        this.dados = dados;
    }
}
