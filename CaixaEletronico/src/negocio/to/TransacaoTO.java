/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.to;

import java.math.BigDecimal;

/**
 *
 * @author 71306587
 */
public class TransacaoTO {
    private ContaTO cliente;
    private ContaTO destino;
    private int tipoTransacao;
    private BigDecimal valor;
    private BigDecimal saldo;
    
    public TransacaoTO (ContaTO c1, ContaTO c2) {
        cliente = c1;
        destino = c2;
    }

    public ContaTO getCliente() {
        return cliente;
    }

    public void setCliente(ContaTO cliente) {
        this.cliente = cliente;
    }

    public ContaTO getDestino() {
        return destino;
    }

    public void setDestino(ContaTO destino) {
        this.destino = destino;
    }
    
    public int getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(int tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }
    
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
    
    /**
     * Constantes
     */
    public static final int tipoSaldo = 1;
    public static final int tipoTransferencia = 2;
    public static final int tipoDeposito = 3;
    public static final int tipoSaque = 4;
}
