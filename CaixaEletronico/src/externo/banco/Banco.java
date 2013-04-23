package externo.banco;

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
    public void aprovarTransferencia () {
    }
    public void aprovarSaque () {
    }
    public void retornarSaldo () {
    }
    public void aprovarDeposito () {
    }
    public void validarSenha () {
    }
    public void validarSessao () {
    }
}
