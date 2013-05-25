/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.log;

import com.atm.business.to.TransactionTO;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Walter
 */
public class LogWriter{
    private final static String LOCAL_ARQUIVO = "C:\\Temp\\log.txt";
    BufferedWriter writer;
    
    public LogWriter() throws IOException {
        FileWriter fstream = new FileWriter(LOCAL_ARQUIVO, true);
        this.writer = new BufferedWriter(fstream);
    }
    
    public void writeLog(String mensagem) throws IOException {
        StringBuilder sb = new StringBuilder();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        sb.append(sdf.format(new Date()));
        sb.append("\t");
        sb.append(mensagem);
        writer.write(sb.toString());
        writer.newLine();
    }
    
    public void writeLog(TransactionTO to) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(getStandardInfo(to));
        sb.append(getAdditionalInfo(to));
        writer.write(sb.toString());
        writer.newLine();
    }
    
    private String getStandardInfo(TransactionTO to) throws IOException {
        StringBuilder sb = new StringBuilder();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        sb.append(sdf.format(new Date()));
        sb.append("\t");
        sb.append(to.getClient().getNumAgency());
        sb.append("\t");
        sb.append(to.getClient().getNumAccount());
        sb.append("\t");
        sb.append(to.getTransactionType());
        sb.append("\t");
        
        return sb.toString();
    }
    
    private String getAdditionalInfo(TransactionTO to) throws IOException {
        StringBuilder sb = new StringBuilder();
        switch(to.getTransactionType()) {
            case TransactionTO.TYPE_BALANCE:
                sb.append(TransactionTO.TEXT_BALANCE);
                sb.append("\t");
                sb.append(to.getBalance());
                break;
            case TransactionTO.TYPE_TRANSFER:
                sb.append(TransactionTO.TEXT_TRANSFER);
                sb.append("\t");
                sb.append(to.getDestiny().getNumAgency());
                sb.append("\t");
                sb.append(to.getDestiny().getNumAccount());
                sb.append("\t");
                sb.append(to.getBalance());
                sb.append("\t");
                sb.append(to.getValue());
                break;
            case TransactionTO.TYPE_DEPOSIT:
                sb.append(TransactionTO.TEXT_DEPOSIT);
                sb.append("\t");
                sb.append(to.getDestiny().getNumAgency());
                sb.append("\t");
                sb.append(to.getDestiny().getNumAccount());
                sb.append("\t");
                sb.append(to.getValue());
                break;
            case TransactionTO.TYPE_WITHDRAW:
                sb.append(TransactionTO.TEXT_WITHDRAW);
                sb.append("\t");
                sb.append(to.getBalance());
                sb.append("\t");
                sb.append(to.getValue());
                break;
        }
        return sb.toString();
    }
    
    public void closeLog() throws IOException {
        writer.close();
    }
}
