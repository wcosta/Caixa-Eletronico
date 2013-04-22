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
    private int nuAgenciaCliente;
    private int nuContaCliente;
    private BigDecimal saldo;
    private int nuAgenciaDestino;
    private int nuContaDestino;
    private BigDecimal valor;

    /* Retorna o número da agência do cliente
     * @return Número da agência do cliente
     */
    public int getNuAgenciaCliente() {
        return nuAgenciaCliente;
    }

    /* Setta o número da conta do cliente
     * @param Número da agência do cliente
     */
    public void setNuAgenciaCliente(int nuAgenciaCliente) {
        this.nuAgenciaCliente = nuAgenciaCliente;
    }

    /* Retorna o número da conta do cliente
     * @return Número da conta do cliente
     */
    public int getNuContaCliente() {
        return nuContaCliente;
    }

    /* Setta o número da conta do cliente
     * @param Número da conta do cliente
     */
    public void setNuContaCliente(int nuContaCliente) {
        this.nuContaCliente = nuContaCliente;
    }

    /* Retorna o saldo do cliente
     * @return saldo do cliente
     */
    public BigDecimal getSaldo() {
        return saldo;
    }

    /* Setta o saldo do cliente
     * @param saldo do cliente
     */
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
    
    /* Retorna uma breve descrição da conta
     * @return descrição da conta
     */
    @Override
    public String toString(){
        return "Agência: " + getNuAgenciaCliente() + " | Conta: " + getNuContaCliente();
    }
    
    public int getNuAgenciaDestino() {
        return nuAgenciaDestino;
    }

    public void setNuAgenciaDestino(int nuAgenciaDestino) {
        this.nuAgenciaDestino = nuAgenciaDestino;
    }

    public int getNuContaDestino() {
        return nuContaDestino;
    }

    public void setNuContaDestino(int nuContaDestino) {
        this.nuContaDestino = nuContaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
