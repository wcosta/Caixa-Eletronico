package test.external;

import com.atm.business.to.AccountTO;
import com.atm.business.to.TransactionTO;
import com.atm.exception.TransactionException;
import com.atm.exception.ValidationException;
import java.math.BigDecimal;

/*
 * Mock do retorno de transações e mensagens do Banco
 */


/**
 *
 * @author 71306587
 */
public class Bank {
    /*
     * Instância única da classe banco
     */
    private static Bank instance;
    /*
     * Retorna instância única de banco
     * @return instância de banco
     */
    public static Bank getInstance(){
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }
    public boolean validatePassword (AccountTO conta) throws ValidationException {
        if(conta.getNumAgency() == 2){
            throw new ValidationException ("Senha incorreta");
        }else{
            return true;
        }
    }
    public boolean validateSession (AccountTO conta) throws ValidationException {
        if(conta.getNumAgency() == 3){
            throw new ValidationException ("Erro na validação do usuário");
        }else{
            return true;
        }
    }
    public BigDecimal getBalance (TransactionTO to) throws TransactionException{
        if(to.getBalance() != null) {
            return to.getBalance();
        }
        BigDecimal saldo;
        switch(to.getClient().getNumAgency()) {
            case 1:
                saldo = new BigDecimal(1000000).setScale(2);
                break;
            case 2:
                saldo = new BigDecimal(2000000).setScale(2);
                break;
            case 3:
                saldo = new BigDecimal(200000).setScale(2);
                break;
            case 4:
                throw new TransactionException(to);
            case 5:
                saldo = new BigDecimal(50000).setScale(2);
                break;
            default:
                saldo = new BigDecimal(0).setScale(2);
                break;
        }
        if(to.getTransactionType() == TransactionTO.TYPE_BALANCE) {
            System.out.println("\nSaldo atual:" + saldo + "\n");
        }
        return saldo;
    }
    
    public TransactionTO approveDeposit (TransactionTO to) throws TransactionException {
        if(to.getClient().getNumAgency() == 4) {
             throw new TransactionException(to);
        } else {
            System.out.println("\nDepósito realizado com sucesso!\n");
            return to;
        }
    }
    public TransactionTO approveTransfer (TransactionTO to) throws TransactionException {
        if(to.getClient().getNumAgency() == 4) {
            throw new TransactionException(to);
        } else if (to.getBalance().intValue() < to.getValue().intValue()) {
            throw new TransactionException("\nErro: saldo insuficiente.\n");
        } else {
            to.setBalance(new BigDecimal(to.getBalance().intValue() - to.getValue().intValue()).setScale(2));
            System.out.println("\nTransferência realizada com sucesso!\n");
            return to;
        }
    }
    public TransactionTO approveWithdraw (TransactionTO to) throws TransactionException {
        if(to.getClient().getNumAgency() == 4) {
            throw new TransactionException(to);
        } else if (to.getBalance().intValue() < to.getValue().intValue()) {
            throw new TransactionException("\nErro: saldo insuficiente.\n");
        } else if ((to.getValue().intValue() % 10) != 0){
            throw new TransactionException("\nErro: valor a ser sacado deve ser múltiplo de 10.\n");
        } else if (to.getValue().intValue() == 0){
            throw new TransactionException("\nErro: valor a ser sacado não pode ser 0.\n");
        } else {
            to.setBalance(new BigDecimal(to.getBalance().intValue() - to.getValue().intValue()).setScale(2));
            System.out.println("\nSaque realizado com sucesso!\n");
            return to;
        }
    }
}
