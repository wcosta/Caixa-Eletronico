/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.controller.impl;

import externo.banco.Banco;
import negocio.controller.TransacaoController;
import negocio.to.ContaTO;
import negocio.to.TransacaoTO;

/**
 *
 * @author 71306587
 */
public class TransacaoControllerImpl implements TransacaoController{
    private TransacaoTO dados;
    
    public boolean validarSenha(ContaTO conta){
        return Banco.getInstance().validarSenha(conta);
    }

    public TransacaoTO getDados() {
        return dados;
    }

    public void setDados(TransacaoTO dados) {
        this.dados = dados;
    }
}
