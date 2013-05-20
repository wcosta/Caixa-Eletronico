/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.factory;

import com.atm.controller.TransactionController;
import com.atm.controller.impl.TransactionControllerImpl;

/**
 *
 * @author 71306587
 */
public class ControllerFactory {
    private static TransactionController transactionController;
    
    public static TransactionController getTCInstance(){
        if(transactionController == null) {
            transactionController = new TransactionControllerImpl();
        }
        return transactionController;
    }
}
